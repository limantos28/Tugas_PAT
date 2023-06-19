var express = require('express');
var bodyparser = require('body-parser');
var fs = require('fs');
var mysql = require('mysql');
var app = express();

app.use(bodyparser.json());

const conn = mysql.createConnection({
    host : '127.0.0.1',
    user : 'root',
    password : '',
    database : 'perpus'
});

conn.connect(function(err){
    if (err) throw err;
    console.log("MySQL connected.....");
});


app.get('/getMahasiswa', function(req,res){
    console.log('Menerima GET request /getMahasiswa');
    let sql = "SELECT * FROM user where role = 0";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/getUser', function(req,res){
    console.log('Menerima GET request /getUser');
    let sql = "SELECT * FROM user ;";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/getAdmin', function(req,res){
    console.log('Menerima GET request /getAdmin');
    let sql = "SELECT * FROM user where role = 1";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});



app.get('/listBukuTersedia', function(req,res){
    console.log('Menerima GET request /listBukuTersedia');
    let sql = "SELECT * FROM buku where status = 0;";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/listBukuTerpinjam', function(req,res){
    console.log('Menerima GET request /listBukuTerpinjam');
    let sql = "SELECT * FROM buku where status =1; ";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/listBukuTerpinjam', function(req,res){
    console.log('Menerima GET request /listBukuTerpinjam');
    let sql = "SELECT * FROM buku where status =1; ";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});


app.post('/insertBuku', function(req,res){
    console.log('Menerima post request /insertBuku');
    let data = {judul_buku: req.body.judul_buku, 
        tahun_terbit:req.body.tahun_terbit,
        author: req.body.author, 
        penerbit:req.body.penerbit,jumlah_halaman:req.body.jumlah_halaman
    };
    let sql = "INSERT INTO buku (judul_buku, tahun_terbit,author,penerbit,jumlah_halaman,status)VALUES ('"+data.judul_buku+"','"+data.tahun_terbit+"','"+data.author+"','"+data.penerbit+"','"+data.jumlah_halaman+"',0);"; 
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/pinjamBuku', function(req,res){
    console.log('Menerima post request /pinjamBuku');
    let data = {username: req.body.username, 
        idbuku:req.body.idbuku};
    let sql = "UPDATE buku SET status = 1, peminjam = '"+data.username+"' WHERE idbuku = '"+
    data.idbuku+"';";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/returnBuku', function(req,res){
    console.log('Menerima post request /returnBuku');
    let data = {username: req.body.username, 
        idbuku:req.body.idbuku};
    let sql = "UPDATE buku SET status = 0 WHERE idbuku = "+
    data.idbuku+";";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/login', function (req, res) {
    console.log('Menerima POST request /login');
    let data = {
        username: req.body.username,
        password: req.body.password
    };

    let sql = "SELECT * FROM user WHERE username = '" + data.username + "' AND password = '" + data.password + "' AND role = 0;";

    conn.query(sql, function (error, results) {
        if (error) {
            console.error('Error executing query:', error);
            res.status(500).json({ message: 'Internal server error' });
        } else {
            if (results.length > 0) {
                res.status(200).json({ message: 'Login successful' });
            } else {
                res.status(401).json({ message: 'Login failed' });
            }
        }
    });
});



var server = app.listen(7000,function(){
    var host = server.address().address;
    var port = server.address().port;
    console.log("Express app listening at http://%s:%s", host,port);
});