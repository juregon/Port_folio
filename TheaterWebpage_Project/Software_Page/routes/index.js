var express = require('express');
var router = express.Router();
var mysql = require('mysql');
var pool = mysql.createPool({
	connectionLimit: 5,
	host: 'localhost',
	user: 'root',
	database: 'theater',
	password: 'mean8592'
});

/* GET home page. */
router.get('/', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));

							res.render('index', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});
					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.post('/', function(req,res,next){
	var userid = req.body.user_id;
	var password = req.body.password;
	var logoutsignal = req.body.logout_signal;
	var logoutid = req.body.logout_id;

	pool.getConnection(function (err, connection) {
		// Use the connectio
		//SET @userid = req.body.user_id;
		var sqlForlogin = "SELECT * FROM clientinfo WHERE id='"+userid+"'";
		var sqlForlogin2 = "UPDATE logindb set login=1";
		var sqlForlogin3 = "UPDATE logindb set login=0";
		var sqlForlogin4 = "UPDATE clientinfo set loginok = '1' where id = '" +userid+ "'";
		var sqlForlogin5 = "UPDATE clientinfo set loginok = '0' where id = '" +logoutid+ "'";

		console.log(sqlForlogin);
		console.log(userid);
		console.log("logout : " + logoutid);
		connection.query(sqlForlogin, function(err, result){
			if(err) console.error("err : " + err);
			console.log("result : " + JSON.stringify(result));

			connection.query(sqlForlogin3, function(err, resultlogout){
				if(err) console.error("err : " + err);
				console.log("resultlogout : " + JSON.stringify(resultlogout));

				if(logoutsignal == 1){
					connection.query(sqlForlogin5, function(err, result5){
						if(err) console.error("err : " + err);
						console.log("result5 : " + JSON.stringify(result5));
					})
					res.redirect('/');
				}
				else{
					if(result.length == 0){
						res.send("<script> alert('존재하지 않는 아이디 입니다.');history.back();</script>");
					}
					else{
						if(result[0].passwd !== password){
							res.send("<script> alert('비밀번호가 틀렸습니다.');history.back();</script>");
						}
						else{
							connection.query(sqlForlogin2, function(err, result2){
								if(err) console.error("err : " + err);
								console.log("result2 : " + JSON.stringify(result2));
							})
							connection.query(sqlForlogin4, function(err, result4){
								if(err) console.error("err : " + err);
								console.log("result4 : " + JSON.stringify(result4));
							})
							res.redirect('/');
						}
					}
				}

			});
			connection.release();
		});
	});
});

router.get('/book-1', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";
		var sqlForbook = "SELECT * from theaterinfo order by time desc";
		var sqlForbooksi = "SELECT * from theaterinfo where area2 = '강남'";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('book-1', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});

							connection.query(sqlForbooksi, function (err,seat) {
							if (err) console.error("err : " + err);
							console.log("seat : " + JSON.stringify(seat));


							var seats = [
                            [1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                       		 ];
                       		 console.log("TLqk222 :: "+ seats);




							res.render('book-1', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2, seats:seats});
							})
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/book-2', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		var sqlForbook = "SELECT * from theaterinfo order by time desc";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('book-2', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/book-3', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		var sqlForbook = "SELECT * from theaterinfo order by time desc";
		console.log("i'm book3");
		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));

							var sqlForbooksi = "SELECT * from theaterinfo where area2 = '강남'";
							connection.query(sqlForbooksi, function (err,seat) {
							if (err) console.error("err : " + err);
							console.log("seat : " + JSON.stringify(seat));
							console.log("knjnxcjvnxckvn :::::::" + seat[0].seat);

							var seats = [
                            [1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                            [1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1],
                       		 ];
                       		 console.log("TLqk222 :: "+ seats);


							res.render('book-3', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2, seat:seat});
							});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/book-4', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";
		var sqlForbook = "SELECT * from theaterinfo order by time desc";
		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));

							var sqlForbook3="select * from theaterinfo where area2='강남'";

							connection.query(sqlForbook3, function (err,seat) {
								if (err) console.error("err : " + err);
								console.log("seat : " + JSON.stringify(seat));
							res.render('book-4', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2, seat:seat});
						});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/book-5', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";
		var sqlForbook = "SELECT * from theaterinfo order by time desc";
		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('book-5', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/movie', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));

							var sqlForSelectList6 = "SELECT * FROM movieinfo ORDER BY score DESC";

							connection.query(sqlForSelectList6, function (err,rows0) {
							if (err) console.error("err : " + err);
							console.log("rows0 : " + JSON.stringify(rows0));

							res.render('movie', {title: ' 영화 정보', rows0:rows0, rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
							});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/theater', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('theater', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/store', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('store', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/customer', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('customer', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/mypage', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));
						var userid = loginsignal[0].id;

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));

							var sqlForbasket = "SELECT * FROM basket where buyer = '" + userid + "'";
							connection.query(sqlForbasket, function(err, basket){
								if (err) console.error("err : " + err);
								console.log("basket : " + JSON.stringify(basket));


							res.render('mypage', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2, basket:basket});
							});

						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.get('/join', function(req, res, next) {
	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForSelectList = "SELECT * FROM movieinfo ORDER BY people DESC";

		connection.query(sqlForSelectList, function (err,rows) {
			if (err) console.error("err : " + err);
			console.log("rows : " + JSON.stringify(rows));

			var sqlForSelectList2 = "SELECT * FROM movieinfo WHERE ing=0 ORDER BY openday DESC";

			connection.query(sqlForSelectList2, function (err,rows2) {
				if (err) console.error("err : " + err);
				console.log("rows2 : " + JSON.stringify(rows2));

				var sqlForSelectList3 = "SELECT * FROM movieinfo WHERE ing=1 ORDER BY openday ";

				connection.query(sqlForSelectList3, function (err,rows3) {
					if (err) console.error("err : " + err);
					console.log("rows3 : " + JSON.stringify(rows3));

					var sqlForSelectList4 = "SELECT * FROM clientinfo ORDER BY loginok DESC";

					connection.query(sqlForSelectList4, function (err,loginsignal) {
						if (err) console.error("err : " + err);
						console.log("loginsignal : " + JSON.stringify(loginsignal));

						var sqlForSelectList5 = "SELECT * FROM logindb";

						connection.query(sqlForSelectList5, function (err,loginsignal2) {
							if (err) console.error("err : " + err);
							console.log("loginsignal2 : " + JSON.stringify(loginsignal2));
							res.render('join', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
						// Don't use the connection here, it has been returned to the pool.
						});

					// Don't use the connection here, it has been returned to the pool.
					});
				// Don't use the connection here, it has been returned to the pool.
				});

				// Don't use the connection here, it has been returned to the pool.
			});
		connection.release();
		});
	});
});

router.post('/join', function(req, res, next) {
	var id = req.body.id;
	var passwd = req.body.passwd;
	var passwdagain = req.body.passwdagain;
	var name = req.body.name;
	var birth = req.body.birth;
	var phone = req.body.phone;
	var email = req.body.email;
	console.log("askaskasksak : " + id);

	pool.getConnection(function (err, connection) {
		// Use the connection
		var sqlForjoin = "SELECT * FROM clientinfo WHERE id='"+id+"'";
		var sqlForjoin2 = "INSERT into clientinfo (`id`, `passwd`, `name`, `birth`, `phone`, `email`) VALUES ('"+ id + "', '" + passwd + "', '" + name + "', '" + birth + "', '" + phone + "', '" + email + "')";

		//connection.query(sqlForjoin, function(err, result){
		//	if (err) console.error("err : " + err);
		//	console.log("result : " + JSON.stringify(result));
		//	if(result[0].id == id){
		//		res.send("<script> alert('이미 존재하는 아이디 입니다.');history.back();</script>");
		//		connection.release();
		//	}
		//	else{
				connection.query(sqlForjoin2, function(err, result2){
					if (err) console.error("err : " + err);
					console.log("result2 : " + JSON.stringify(result2));
				})
				res.redirect('/');
				connection.release();

		//	}
		//})
	});
});

router.post('/store', function(req,res,next){
	var name = req.body.name;
	var price = req.body.price;
	var amount = req.body.amount;
	var buyer = req.body.buyer;

	console.log("name : " + name);
	console.log("amount : " + amount);
	console.log("buyer : " + buyer);
	pool.getConnection(function (err, connection) {
		var sqlForbasket = "INSERT into basket(`name`, `amount`, `buyer`, `price`) VALUES ('" + name + "', '" + amount + "', '" + buyer + "', '" + price + "')";

		connection.query(sqlForbasket, function(err, result){
			if(err) console.error("err : ", err);
			console.log("result : " + JSON.stringify(result));
			res.redirect('/store');
			connection.release();
		})

	});
});

router.post('/mypage', function(req,res,next){
	var button = req.body.button;
	var userid = req.body.user_id;
	var password = req.body.password;
	var logoutsignal = req.body.logout_signal;
	var logoutid = req.body.logout_id;
	var curpasswd = req.body.curpasswd;
	var curpasswdagain = req.body.curpasswdagain;
	var newpasswd = req.body.newpasswd;
	var newpasswdagain = req.body.newpasswdagain;
	var name = req.body.name;
	var phone = req.body.phone;
	var email = req.body.email;
	var birth = req.body.birth;


	pool.getConnection(function (err, connection) {
		// Use the connectio
		var sqlForchange = "SELECT * FROM clientinfo WHERE id='"+userid+"' and passwd='" +password+ "'";
		var sqlForchange2 = "UPDATE clientinfo set passwd='" +newpasswd+ "' where id='" +userid+ "'";
		var sqlForchange3 = "UPDATE clientinfo set name='" +name+ "', phone='" +phone+ "', email='" +email+ "', birth='" +birth+ "' where id='" +userid+ "'";
		var sqlForchange4 = "DELETE from clientinfo where id='" +userid+ "'";
		var sqlForchange5 = "UPDATE logindb set login='0';"

		if(button == 'withdrawal'){
			connection.query(sqlForchange, function(err, result){
				if(err) console.error("err : " + err);
				console.log("result : " + JSON.stringify(result));

				if(result[0].passwd != curpasswd){
				res.send("<script> alert('비밀번호가 틀렸습니다.');history.back();</script>");
				}
				else if(curpasswd != curpasswdagain){
					res.send("<script> alert('비밀번호가 틀렸습니다.');history.back();</script>");
				}
				else{
					connection.query(sqlForchange4, function(err, result3){
						if(err) console.error("err : " + err);
						console.log("result3 : " + JSON.stringify(result3));
						connection.query(sqlForchange5, function(err, result4){
							if(err) console.error("err : " + err);
							console.log("result4 : " + JSON.stringify(result4));
							res.redirect('/')
						});
					});
					connection.release();
				}
			})


		}
		else if(button == 'info'){
			if(name == ''){
				res.send("<script> alert('이름을 입력 해 주세요.');history.back();</script>");
			}
			else if(phone == ''){
				res.send("<script> alert('연락처를 입력 해 주세요.');history.back();</script>");
			}
			else if(email == ''){
				res.send("<script> alert('이메일을 입력 해 주세요.');history.back();</script>");
			}
			else if(birth == ''){
				res.send("<script> alert('생년월일을 입력 해 주세요.');history.back();</script>");
			}
			else{
				connection.query(sqlForchange3, function(err, result3){
					if(err) console.error("err : " + err);
					console.log("result3 : " + JSON.stringify(result3));

					res.send("<script> alert('개인 정보가 성공적으로 변경 되었습니다.');history.back();</script>");
					connection.release();
				});
			}
		}
		else{
			connection.query(sqlForchange, function(err, result){
				if(err) console.error("err : " + err);
				console.log("result : " + JSON.stringify(result));

				if(result[0].passwd != curpasswd){
					res.send("<script> alert('비밀번호가 틀렸습니다.');history.back();</script>");
				}
				else{
					if(newpasswd != newpasswdagain){
						res.send("<script> alert('바꾸려는 비밀번호가 일치하지 않습니다.');history.back();</script>");
					}
					else{
						connection.query(sqlForchange2, function(err, result2){
							if(err) console.error("err : " + err);
							console.log("result2 : " + JSON.stringify(result2));
						});
						res.send("<script> alert('비밀번호가 성공적으로 변경 되었습니다.');history.back();</script>");
					}
				}

				connection.release();
			});
		}
	});
});

router.post('/customer', function(req,res,next){
	var name = req.body.name;
	var phone = req.body.phone;
	var email = req.body.email;
	var title = req.body.title;
	var content = req.body.content;

	pool.getConnection(function (err, connection) {
		var sqlForqna = "INSERT into inquiry(`phone`, `writer`, `email`, `title`, `content`) VALUES ('" + phone + "', '" + name + "', '" + email + "' , '" + title + "', '" + content + "')";

		connection.query(sqlForqna, function(err, result){
			if(err) console.error("err : ", err);
			console.log("result : " + JSON.stringify(result));
			res.send("<script> alert('빠른 시일 내로 답변 드리겠습니다. 감사합니다.');history.back();</script>");
			connection.release();
		})

	});
});


module.exports = router;
