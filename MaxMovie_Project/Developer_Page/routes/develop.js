var express = require('express');
var router = express.Router();
var mysql = require('mysql');
var pool = mysql.createPool({
  connectionLimit: 5,
  host: 'localhost',
  user: 'root',
  database: 'theater',
  password: 'cndgus78'
});

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

              res.render('developer_login', {title: ' 영화 정보', rows: rows, rows2:rows2, rows3:rows3, loginsignal:loginsignal, loginsignal2:loginsignal2});
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

router.get('/clientinfo', function(req, res, next) { 
  pool.getConnection(function(err, connection){
    var sqlForclient = "SELECT * from clientinfo"
    connection.query(sqlForclient, function(err, rows){
      if (err) console.error("result : " + err);
      console.log("rows : " + JSON.stringify(rows));
      console.log("asldkansdlknalxknaslxxmasm :::: " + rows)
      res.render('clientinfo', {title: ' 영화 정보', rows:rows});
    connection.release();
    })
  })
});

router.get('/clientinfo_read/:id', function(req, res, next) {
  var id = req.params.id; // 테이블 id값 변수에 저장

  pool.getConnection(function(err, connection){
    var sqlForclientinfo = "SELECT * from ticketinfo where buyer='" + id + "'";
    var sqlForclientinfo2 = "SELECT * from storeinfo where buyer='" + id + "'";

    connection.query(sqlForclientinfo, function(err, row){
      if (err) console.error("row : " + err);
      console.log("row : " + JSON.stringify(row));
      connection.query(sqlForclientinfo2, function(err, row2){
      if (err) console.error("row2 : " + err);
      console.log("row2 : " + JSON.stringify(row2));

      res.render('clientinfo_read', {row:row, row2:row2});

      });
    connection.release();
    })
  })
});

router.get('/movieinfo', function(req, res, next) {
  pool.getConnection(function(err, connection){
    var sqlFormovie = "SELECT * from movieinfo order by openday desc"
    connection.query(sqlFormovie, function(err, rows){
      if (err) console.error("result : " + err);
      console.log("rows : " + JSON.stringify(rows));

      res.render('movieinfo', {title: ' 영화 정보', rows:rows});
    connection.release();
    })
  })
});

router.get('/movieinfo_read/:title', function(req, res, next) {
  var title = req.params.title; // 테이블 id값 변수에 저장

  pool.getConnection(function(err, connection){
    var sqlFormovieinfo = "SELECT * from theaterinfo where title='" + title + "'";
    connection.query(sqlFormovieinfo, [title], function(err, row){
      if (err) console.error("result : " + err);
      console.log("row : " + JSON.stringify(row));

      res.render('movieinfo_read', {title: ' 영화 정보', row:row});
    connection.release();
    })
  })
});

router.get('/movieinfo_update', function(req, res, next) {
  var idx = req.param.idx; // 테이블 id값 변수에 저장


      // if문을 통해 에러 처리 및 콘솔창 출력
      res.render('movieinfo_update', {}); // 데이터 전송

});

router.get('/movieinfo_write', function(req, res, next) {
  // 로그인 디비에 저장할 데이터들 변수에 저장


      // if문을 통해 에러 처리 및 콘솔창 출력
      res.render('movieinfo_write', {}); // logindb_list로 이동

});

router.get('/movieinfo_write2', function(req, res, next) {
  // 로그인 디비에 저장할 데이터들 변수에 저장


      // if문을 통해 에러 처리 및 콘솔창 출력
      res.render('movieinfo_write2', {}); // logindb_list로 이동

});

router.post('/', function(req,res,next){
  var userid = req.body.username;
  var password = req.body.password;

  pool.getConnection(function (err, connection) {
    // Use the connectio
    //SET @userid = req.body.user_id;
    var sqlForlogin = "SELECT * FROM manager WHERE id='"+userid+"'";
    var sqlForlogin2 = "UPDATE logindb set login=1";
    var sqlForlogin3 = "UPDATE logindb set login=0";

    connection.query(sqlForlogin, function(err, result){
      if(err) console.error("err : " + err);
      console.log("result : " + JSON.stringify(result));

      connection.query(sqlForlogin3, function(err, resultlogout){
        if(err) console.error("err : " + err);
        console.log("resultlogout : " + JSON.stringify(resultlogout));

        if(result.length == 0){
          res.send("<script> alert('존재하지 않는 아이디 입니다.');history.back();</script>");
        }
        else{
          if(result[0].passwd !== password){
            res.send("<script> alert('비밀번호가 틀렸습니다.');history.back();</script>");
          }
          else{
            res.redirect('/clientinfo');
          }
        }
      });
      connection.release();
    });
  });
});

router.post('/movieinfo', function(req, res, next) {
  var title = req.body.title; // 테이블 id값 변수에 저장

  pool.getConnection(function(err, connection){
    var sqlFormovieinfodel = "delete from movieinfo where title='" + title + "'";
    connection.query(sqlFormovieinfodel, [title], function(err, row){
      if (err) console.error("result : " + err);
      console.log("row : " + JSON.stringify(row));
      console.log("hiru3 : " +row);

      res.redirect('movieinfo');
    connection.release();
    })
  })
});

router.post('/movieinfo_read/:title', function(req, res, next) {
  var title = req.params.title; // 테이블 id값 변수에 저장
  var area1 = req.body.area1;
  var area2 = req.body.area2;
  var theaternum = req.body.theaternum;

  pool.getConnection(function(err, connection){
    var sqlFormovieinfo = "delete from theaterinfo where title='" + title + "' and area1='" + area1 +"' and area2='" + area2 + "' and theaternum='" + theaternum +"'";
    connection.query(sqlFormovieinfo, [title], function(err, row){
      if (err) console.error("result : " + err);
      console.log("row : " + JSON.stringify(row));
      console.log("hiru : " +area1);
      console.log("hiru2 : " +sqlFormovieinfo);
      console.log("hiru3 : " +row);

      res.redirect(title);
    connection.release();
    })
  })
});

router.post('/clientinfo', function(req, res, next) {
  var userid = req.body.user_id;

  pool.getConnection(function(err, connection){
    var sqlForclient = "DELETE from clientinfo where id='" + userid + "'"
    var sqlForclient2 = "DELETE from ticketinfo where buyer='" + userid + "'"
    var sqlForclient3 = "DELETE from storeinfo where buyer='" + userid + "'"

    connection.query(sqlForclient, function(err, rows){
      if (err) console.error("rows : " + err);
      connection.query(sqlForclient2, function(err, rows2){
        if (err) console.error("rows2 : " + err);
        connection.query(sqlForclient3, function(err, rows3){
          if (err) console.error("rows3 : " + err);

          res.redirect('/clientinfo');
        });
      });
    connection.release();
    })
  })
});

router.post('/clientinfo_write', function(req, res, next) {
  // 로그인 디비에 저장할 데이터들 변수에 저장


      // if문을 통해 에러 처리 및 콘솔창 출력
      res.redirect('/clientinfo'); // logindb_list로 이동

});

router.post('/movieinfo_write', function(req, res, next) {
  // 로그인 디비에 저장할 데이터들 변수에 저장
  var title = req.body.title;
  var grade = req.body.grade;
  var genre = req.body.genre;
  var openday = req.body.openday;
  var director = req.body.director;
  var actor = req.body.actor;
  var time = req.body.time;
  var ing = req.body.ing;
  var image = req.body.image;
  var image2 = req.body.image2;

  pool.getConnection(function (err, connection) {
    // Use the connectio
    var sqlFormovieadd = "INSERT into movieinfo (`title`, `grade`, `genre`, `openday`, `director`, `actor`, `time`, `ing`, `image`, `image2`) VALUES('"+title+"', '"+grade+"', '"+genre+"', '"+openday+"', '"+director+"', '"+actor+"', '"+time+"', '"+ing+"', '"+image+"', '"+image2+"')";

    connection.query(sqlFormovieadd, function(err, result){
      if(err) console.error("err : " + err);
      console.log("result : " + JSON.stringify(result));

      res.redirect('/movieinfo'); // logindb_list로 이동
      connection.release();
    });
  });
});

router.post('/movieinfo_write2', function(req, res, next) {
  // 로그인 디비에 저장할 데이터들 변수에 저장
  var title = req.body.title;
  var area1 = req.body.area1;
  var area2 = req.body.area2;
  var theaternum = req.body.theaternum;
  var time = req.body.time;

  pool.getConnection(function (err, connection) {
    // Use the connectio
    var sqlFormovieadd2 = "INSERT into theaterinfo (`title`, `area1`, `area2`, `theaternum`, `time`) VALUES('"+title+"', '"+area1+"', '"+area2+"', '"+theaternum+"', '"+time+"')";

    connection.query(sqlFormovieadd2, function(err, result){
      if(err) console.error("err : " + err);
      console.log("result : " + JSON.stringify(result));

      res.redirect('/movieinfo'); // logindb_list로 이동
      connection.release();
    });
  });
});

router.post('/movieinfo_update', function(req, res, next) {
  // 로그인 디비에 저장할 데이터들 변수에 저장


      // if문을 통해 에러 처리 및 콘솔창 출력
      res.redirect('/logindb_list'); // logindb_list로 이동

});

module.exports = router;