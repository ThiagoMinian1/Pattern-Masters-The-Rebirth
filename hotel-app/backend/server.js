require('dotenv').config();
const express = require('express');
const mysql = require('mysql2/promise');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());
app.use(express.static('../frontend'));

const dbConfig = {
  host:     process.env.DB_HOST     || '127.0.0.1',
  port:     process.env.DB_PORT     || 3306,
  user:     process.env.DB_USER     || 'root',
  password: process.env.DB_PASSWORD || '',
  database: process.env.DB_NAME     || 'hotel_patternmasters'
};

let pool;
(async () => {
  pool = mysql.createPool(dbConfig);
  try {
    await pool.query('SELECT 1');
    console.log('✅ Conectado a MySQL/MariaDB');
  } catch (e) {
    console.error('❌ Error de conexión:', e.message);
  }
})();

// ─── HABITACIONES (Factory Method) ───────────────────────────────────────────
app.get('/api/habitaciones', async (req, res) => {
  const [rows] = await pool.query('SELECT * FROM habitacion ORDER BY numero_habitacion');
  res.json(rows);
});

app.post('/api/habitaciones', async (req, res) => {
  const { numero_habitacion, tipo_habitacion, estado_habitacion, precioBase_habitacion } = req.body;
  const [result] = await pool.query(
    'INSERT INTO habitacion (numero_habitacion, tipo_habitacion, estado_habitacion, precioBase_habitacion) VALUES (?,?,?,?)',
    [numero_habitacion, tipo_habitacion, estado_habitacion, precioBase_habitacion]
  );
  res.json({ id: result.insertId });
});

app.put('/api/habitaciones/:id', async (req, res) => {
  const { tipo_habitacion, estado_habitacion, precioBase_habitacion } = req.body;
  await pool.query(
    'UPDATE habitacion SET tipo_habitacion=?, estado_habitacion=?, precioBase_habitacion=? WHERE id_habitacion=?',
    [tipo_habitacion, estado_habitacion, precioBase_habitacion, req.params.id]
  );
  res.json({ ok: true });
});

app.delete('/api/habitaciones/:id', async (req, res) => {
  await pool.query('DELETE FROM habitacion WHERE id_habitacion=?', [req.params.id]);
  res.json({ ok: true });
});

// ─── HUÉSPEDES ────────────────────────────────────────────────────────────────
app.get('/api/huespedes', async (req, res) => {
  const [rows] = await pool.query('SELECT * FROM huesped ORDER BY nombre_huesped');
  res.json(rows);
});

app.post('/api/huespedes', async (req, res) => {
  try {
    const { nombre_huesped, email_huesped, telefono_huesped } = req.body;

    if (!nombre_huesped || nombre_huesped.trim().length < 3) {
      return res.status(400).json({
        error: 'El nombre debe tener al menos 3 caracteres'
      });
    }

    const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    if (!emailRegex.test(email_huesped)) {
      return res.status(400).json({
        error: 'Email inválido'
      });
    }

    const telefonoRegex = /^\d{10,11}$/;

    if (!telefonoRegex.test(telefono_huesped)) {
      return res.status(400).json({
        error: 'El teléfono debe tener 10 u 11 dígitos'
      });
    }

    const [existe] = await pool.query(
      'SELECT id_huesped FROM huesped WHERE email_huesped=?',
      [email_huesped]
    );

    if (existe.length > 0) {
      return res.status(400).json({
        error: 'Ya existe un huésped con ese email'
      });
    }

    const [result] = await pool.query(
      'INSERT INTO huesped (nombre_huesped, email_huesped, telefono_huesped) VALUES (?,?,?)',
      [
        nombre_huesped.trim(),
        email_huesped.trim(),
        telefono_huesped.trim()
      ]
    );

    res.json({
      ok: true,
      id: result.insertId
    });

  } catch (error) {
    console.error(error);

    res.status(500).json({
      error: 'Error al crear huésped'
    });
  }
});

app.delete('/api/huespedes/:id', async (req, res) => {
  await pool.query('DELETE FROM huesped WHERE id_huesped=?', [req.params.id]);
  res.json({ ok: true });
});

// ─── RESERVAS (Builder + State + Observer) ───────────────────────────────────
app.get('/api/reservas', async (req, res) => {
  const [rows] = await pool.query(`
    SELECT r.*, h.nombre_huesped, h.email_huesped, hab.numero_habitacion, hab.tipo_habitacion, hab.precioBase_habitacion
    FROM reserva r
    JOIN huesped h ON r.huesped_id = h.id_huesped
    JOIN habitacion hab ON r.habitacion_id = hab.id_habitacion
    ORDER BY r.fecha_inicio DESC
  `);
  res.json(rows);
});

app.post('/api/reservas', async (req, res) => {
  const { fecha_inicio, fecha_fin, huesped_id, habitacion_id } = req.body;

  const inicio = new Date(fecha_inicio);
  const fin = new Date(fecha_fin);

  if (fin <= inicio) {
    return res.status(400).json({
      error: 'La fecha de salida debe ser mayor que la fecha de entrada'
    });
  }

  // Si la habitación está ocupada, la reserva va directo a LISTA_ESPERA (Observer)
  const [[hab]] = await pool.query('SELECT estado_habitacion FROM habitacion WHERE id_habitacion=?', [habitacion_id]);
  const estadoInicial = (hab && hab.estado_habitacion === 'OCUPADA') ? 'LISTA_ESPERA' : 'PENDIENTE';

  // Verificar que no haya reservas activas que se superpongan en fechas para esa habitación
  const [solapadas] = await pool.query(`
    SELECT id_reserva FROM reserva
    WHERE habitacion_id = ?
      AND estado_reserva NOT IN ('CANCELADA', 'COMPLETADA')
      AND fecha_inicio < ? AND fecha_fin > ?
  `, [habitacion_id, fecha_fin, fecha_inicio]);

  if (solapadas.length > 0) {
    return res.status(400).json({ error: 'Ya existe una reserva para esa habitación en esas fechas' });
  }

  const [result] = await pool.query(
    'INSERT INTO reserva (fecha_inicio, fecha_fin, estado_reserva, huesped_id, habitacion_id) VALUES (?,?,?,?,?)',
    [fecha_inicio, fecha_fin, estadoInicial, huesped_id, habitacion_id]
  );

  res.json({ id: result.insertId, estado: estadoInicial });
});

app.put('/api/reservas/:id/estado', async (req, res) => {
  const { estado } = req.body;
  const estadosValidos = ['PENDIENTE', 'CONFIRMADA', 'CANCELADA', 'LISTA_ESPERA', 'COMPLETADA'];
  if (!estadosValidos.includes(estado)) return res.status(400).json({ error: 'Estado inválido' });
  await pool.query('UPDATE reserva SET estado_reserva=? WHERE id_reserva=?', [estado, req.params.id]);
  res.json({ ok: true });
});

app.delete('/api/reservas/:id', async (req, res) => {
  await pool.query('DELETE FROM reserva WHERE id_reserva=?', [req.params.id]);
  res.json({ ok: true });
});

// ─── ESTADÍAS (Facade: Check-in / Check-out) ──────────────────────────────────
app.get('/api/estadias', async (req, res) => {
  const [rows] = await pool.query(`
    SELECT e.*,
           r.huesped_id,
           h.nombre_huesped,
           hab.numero_habitacion,
           hab.tipo_habitacion,
           hab.precioBase_habitacion,
           f.id_factura,
           f.total
    FROM estadia e
    JOIN reserva r ON e.reserva_id = r.id_reserva
    JOIN huesped h ON r.huesped_id = h.id_huesped
    JOIN habitacion hab ON r.habitacion_id = hab.id_habitacion
    LEFT JOIN factura f ON f.id_estadia = e.id_estadia
    ORDER BY e.fechaCheckIn DESC
  `);

  res.json(rows);
});

// Check-in: crea estadía + pone habitación en "OCUPADA" + reserva CONFIRMADA
app.post('/api/checkin', async (req, res) => {
  const { reserva_id, fechaCheckIn, fechaCheckOut } = req.body;
  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();
    const [existing] = await conn.query('SELECT id_estadia FROM estadia WHERE reserva_id=?', [reserva_id]);
    if (existing.length > 0) throw new Error('Ya existe una estadía para esta reserva');
    const [r] = await conn.query('SELECT habitacion_id FROM reserva WHERE id_reserva=?', [reserva_id]);
    const habitacion_id = r[0].habitacion_id;
    await conn.query(
      'INSERT INTO estadia (reserva_id, fechaCheckIn, fechaCheckOut) VALUES (?,?,?)',
      [reserva_id, fechaCheckIn, fechaCheckOut]
    );
    await conn.query('UPDATE habitacion SET estado_habitacion="OCUPADA" WHERE id_habitacion=?', [habitacion_id]);
    await conn.query('UPDATE reserva SET estado_reserva="CONFIRMADA" WHERE id_reserva=?', [reserva_id]);
    await conn.commit();
    res.json({ ok: true });
  } catch (e) {
    await conn.rollback();
    res.status(400).json({ error: e.message });
  } finally {
    conn.release();
  }
});

app.post('/api/estadias/:id/servicios', async (req, res) => {
  try {

    const id_estadia = req.params.id;
    const { nombre, precio_unitario, noches } = req.body;

    const nochesAplicadas = Number(noches) || 1;
    const precioUnit = Number(precio_unitario);
    const costoTotal = precioUnit * nochesAplicadas;

    await pool.query(
      `INSERT INTO servicio_extra
       (id_estadia, nombre_servicio, precio_unitario, noches_aplicadas, costo)
       VALUES (?,?,?,?,?)`,
      [id_estadia, nombre, precioUnit, nochesAplicadas, costoTotal]
    );

    res.json({ ok: true, costo: costoTotal });

  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

app.get('/api/estadias/:id/servicios', async (req, res) => {

  const [rows] = await pool.query(
    `SELECT *
     FROM servicio_extra
     WHERE id_estadia=?`,
    [req.params.id]
  );

  res.json(rows);
});

// Check-out: crea factura (habitación + servicios extra - descuento promo) + libera habitación + completa reserva
app.post('/api/checkout', async (req, res) => {
  const { estadia_id, total, promocion_id } = req.body;
  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();
    const [e] = await conn.query(`
      SELECT e.reserva_id, r.habitacion_id, hab.precioBase_habitacion,
             DATEDIFF(e.fechaCheckOut, e.fechaCheckIn) as noches
      FROM estadia e
      JOIN reserva r ON e.reserva_id = r.id_reserva
      JOIN habitacion hab ON r.habitacion_id = hab.id_habitacion
      WHERE e.id_estadia=?`, [estadia_id]);
    if (!e.length) throw new Error('Estadía no encontrada');
    const { reserva_id, habitacion_id, precioBase_habitacion, noches } = e[0];

    // Servicios extra cargados para esta estadía (patrón Decorator)
    const [[{ totalServicios }]] = await conn.query(
      'SELECT COALESCE(SUM(costo),0) as totalServicios FROM servicio_extra WHERE id_estadia=?',
      [estadia_id]
    );

    const montoEstadia = precioBase_habitacion * Math.max(noches, 1);
    const subtotal = montoEstadia + Number(totalServicios);

    // Promoción opcional (patrón Strategy): descuento % sobre el subtotal
    let descuentoAplicado = 0;
    let promoNombre = null;
    if (promocion_id) {
      const [promo] = await conn.query(
        'SELECT nombre_promocion, descuento FROM promocion WHERE id_promocion=?',
        [promocion_id]
      );
      if (promo.length) {
        descuentoAplicado = Number(promo[0].descuento);
        promoNombre = promo[0].nombre_promocion;
      }
    }

    const montoFinal = total || (subtotal - (subtotal * descuentoAplicado / 100));

    const [factExist] = await conn.query('SELECT id_factura FROM factura WHERE id_estadia=?', [estadia_id]);
    if (factExist.length > 0) throw new Error('Ya existe una factura para esta estadía');
    await conn.query('INSERT INTO factura (id_estadia, total) VALUES (?,?)', [estadia_id, montoFinal]);
    await conn.query('UPDATE habitacion SET estado_habitacion="DISPONIBLE" WHERE id_habitacion=?', [habitacion_id]);
    await conn.query('UPDATE reserva SET estado_reserva="COMPLETADA" WHERE id_reserva=?', [reserva_id]);

    // ── OBSERVER: al liberar la habitación, promover la primera reserva en lista de espera
    // que tenga asignada esa misma habitación
    const [enEspera] = await conn.query(`
      SELECT id_reserva FROM reserva
      WHERE habitacion_id = ? AND estado_reserva = 'LISTA_ESPERA'
      ORDER BY id_reserva ASC
      LIMIT 1
    `, [habitacion_id]);
    let promovida = null;
    if (enEspera.length > 0) {
      const id_promovida = enEspera[0].id_reserva;
      await conn.query(
        "UPDATE reserva SET estado_reserva='PENDIENTE' WHERE id_reserva=?",
        [id_promovida]
      );
      promovida = id_promovida;
    }

    await conn.commit();
    res.json({
      ok: true,
      total: montoFinal,
      montoEstadia,
      totalServicios: Number(totalServicios),
      descuentoAplicado,
      promoNombre,
      reserva_promovida: promovida  // null si no había nadie en espera
    });
  } catch (e) {
    await conn.rollback();
    res.status(400).json({ error: e.message });
  } finally {
    conn.release();
  }
});

// ─── FACTURAS (Template Method: pagos) ───────────────────────────────────────
app.get('/api/facturas', async (req, res) => {
  const [rows] = await pool.query(`
    SELECT f.*, e.fechaCheckIn, e.fechaCheckOut, h.nombre_huesped, hab.numero_habitacion
    FROM factura f
    JOIN estadia e ON f.id_estadia = e.id_estadia
    JOIN reserva r ON e.reserva_id = r.id_reserva
    JOIN huesped h ON r.huesped_id = h.id_huesped
    JOIN habitacion hab ON r.habitacion_id = hab.id_habitacion
    ORDER BY f.id_factura DESC
  `);
  res.json(rows);
});

// ─── PROMOCIONES (Strategy: estrategias de costo) ────────────────────────────
app.get('/api/promociones', async (req, res) => {
  const [rows] = await pool.query('SELECT * FROM promocion ORDER BY descuento DESC');
  res.json(rows);
});

app.post('/api/promociones', async (req, res) => {
  const { nombre_promocion, descuento } = req.body;
  const [result] = await pool.query(
    'INSERT INTO promocion (nombre_promocion, descuento) VALUES (?,?)',
    [nombre_promocion, descuento]
  );
  res.json({ id: result.insertId });
});

app.delete('/api/promociones/:id', async (req, res) => {
  await pool.query('DELETE FROM promocion WHERE id_promocion=?', [req.params.id]);
  res.json({ ok: true });
});

// ─── LISTA DE ESPERA (Observer) ──────────────────────────────────────────────
app.get("/api/lista-espera", async (req, res) => {
  try {
    const [rows] = await pool.query(`
      SELECT r.id_reserva, r.fecha_inicio, r.fecha_fin, r.estado_reserva,
             h.nombre_huesped, h.email_huesped,
             hab.numero_habitacion, hab.tipo_habitacion, hab.precioBase_habitacion
      FROM reserva r
      JOIN huesped h ON r.huesped_id = h.id_huesped
      JOIN habitacion hab ON r.habitacion_id = hab.id_habitacion
      WHERE r.estado_reserva = 'LISTA_ESPERA'
      ORDER BY r.id_reserva ASC
    `);
    res.json(rows);
  } catch (e) { res.status(500).json({ error: e.message }); }
});

app.put("/api/lista-espera/:id", async (req, res) => {
  try {
    const [result] = await pool.query(
      "UPDATE reserva SET estado_reserva='LISTA_ESPERA' WHERE id_reserva=? AND estado_reserva='PENDIENTE'",
      [req.params.id]
    );
    if (result.affectedRows === 0)
      return res.status(400).json({ error: "Solo se pueden poner en lista reservas PENDIENTE" });
    res.json({ ok: true });
  } catch (e) { res.status(500).json({ error: e.message }); }
});

// ─── STATS para dashboard ─────────────────────────────────────────────────────
app.get('/api/stats', async (req, res) => {
  const [[{ total_reservas }]] = await pool.query('SELECT COUNT(*) as total_reservas FROM reserva');
  const [[{ habitaciones_disponibles }]] = await pool.query("SELECT COUNT(*) as habitaciones_disponibles FROM habitacion WHERE estado_habitacion='DISPONIBLE'");
  const [[{ habitaciones_ocupadas }]] = await pool.query("SELECT COUNT(*) as habitaciones_ocupadas FROM habitacion WHERE estado_habitacion='OCUPADA'");
  const [[{ total_huespedes }]] = await pool.query('SELECT COUNT(*) as total_huespedes FROM huesped');
  const [[{ ingresos_total }]] = await pool.query('SELECT COALESCE(SUM(total),0) as ingresos_total FROM factura');
  const [[{ lista_espera }]] = await pool.query("SELECT COUNT(*) as lista_espera FROM reserva WHERE estado_reserva='LISTA_ESPERA'");
  res.json({ total_reservas, habitaciones_disponibles, habitaciones_ocupadas, total_huespedes, ingresos_total, lista_espera });
});

// Manejador de errores global
app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).json({ error: 'Error interno del servidor' });
});

const PORT = process.env.PORT || 3001;
app.listen(PORT, () => console.log(`🏨 Hotel API corriendo en http://localhost:${PORT}`));