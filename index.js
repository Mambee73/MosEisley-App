// 1. Importar dependencias
const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');

const app = express();
const PORT = 3000;

// 2. Middlewares
app.use(cors());
app.use(express.json()); // 👈 necesario para leer req.body

// 3. Conexión a MySQL
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',                 // tu usuario MySQL
  password: 'Prisma23021612',   // tu clave MySQL
  database: 'mercado_mos_eisley' // tu base creada en Workbench
});

db.connect(err => {
  if (err) {
    console.error('Error conectando a MySQL:', err);
    return;
  }
  console.log('Conectado a MySQL');
});

// ------------------- ENDPOINTS -------------------

// 🔹 Usuarios
// Insertar usuario
app.post('/api/usuarios', (req, res) => {
  const { nombre, correo, clave, imagenUri } = req.body;
  const sql = 'INSERT INTO usuarios (nombre, correo, clave, imagenUri) VALUES (?, ?, ?, ?)';
  db.query(sql, [nombre, correo, clave, imagenUri], (err, result) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: result.insertId, nombre, correo, clave, imagenUri });
  });
});

// Listar usuarios
app.get('/api/usuarios', (req, res) => {
  db.query('SELECT * FROM usuarios', (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results);
  });
});

// 🔹 Productos
// Insertar producto
app.post('/api/productos', (req, res) => {
  const { nombre, descripcion, precio, categoria, imagen_url, usuario_id } = req.body;
  const sql = 'INSERT INTO productos (nombre, descripcion, precio, categoria, imagen_url, usuario_id) VALUES (?, ?, ?, ?, ?, ?)';
  db.query(sql, [nombre, descripcion, precio, categoria, imagen_url, usuario_id], (err, result) => {
    if (err) return res.status(500).send(err);
    res.status(201).json({ id: result.insertId, nombre, descripcion, precio, categoria, imagen_url, usuario_id });
  });
});

// Listar productos
app.get('/api/productos', (req, res) => {
  db.query('SELECT * FROM productos', (err, results) => {
    if (err) return res.status(500).send(err);
    res.json(results);
  });
});

// ------------------- INICIAR SERVIDOR -------------------
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
