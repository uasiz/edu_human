var express = require('express');
var router = express.Router();
var db = require('./db/con_db')();
//[body parser]------------------------------------------------------------------------//
var bodyParser = require('body-parser')
var jsonParser = bodyParser.json();
var urlencodedParser = bodyParser.urlencoded({ extended: false });
//------------------------------------------------------------------------------------//
//[session]---------------------------------------------------------------------------//
var session = require('express-session');

router.use(session({
  secret: '9+PF3ku:x9lV2#(%L3..ZJN^{7LXg+}',
  resave: false,
  saveUninitialized: true
}));
//------------------------------------------------------------------------------------//
var fs = require('fs');//base64 이미지 저장 관련



router.get('/', function(req, res){
  if(req.session.authId)
      res.redirect(
        (req.session.authId=='human')
        ?'VacationLately'//admin 초기 페이지
        :'RestSelect'//student 초기 페이지
      );
	else
    res.render('index');
});
router.get('/StudentAdd', function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    var sql="select * from hm_subject";
    db.select(sql,function(err,result){
      if (err) 
      console.log(err);
      else {
        res.render('admin/StudentAdd',{
          subject_result:result
        });
      }
    });
  }else
    res.render('index');
});
router.get('/VacationLately', function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    var sql="select * from hm_rest_table where verified=0";
    db.select(sql,function(err,result){
      if (err) 
      console.log(err);
      else {
        res.render('admin/VacationLately',{
          rest_result : result
        });
      }
    });
  }else
    res.render('index');
});
router.get('/VacationSelect', function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    var sql="select * from hm_rest_table";
    db.select(sql,function(err,result){
      if (err) 
      console.log(err);
      else {
        res.render('admin/VacationSelect',{
          rest_result : result
        });
      }
    });
  }else
    res.render('index');
});
router.get('/StudentSelect', function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    var sql="select * from hm_student";
    db.select(sql,function(err,result){
      if (err) 
      console.log(err);
      else {
        res.render('admin/StudentSelect',{
          student_result : result
        });
      }
    });
  }else
    res.render('index');
});
router.get('/SignPage', function(req, res){
  if(req.session.authId){
    res.render('student/SignPage');
  }else
    res.render('index');
});
router.get('/RestReport', function(req, res){
  if(req.session.authId){
    res.render('student/RestReport',{ 
      stu_name : req.session.user.stu_name,
      available_rest : req.session.user.available_rest
    });
  }else
    res.render('index');
});
router.get('/RestSelect', function(req, res){
  if(req.session.authId){
    var sql="select * from hm_rest_table where stu_number='"+req.session.user.stu_number+"'";
    db.select(sql,function(err,result){
      if (err) 
      console.log(err);
      else {
        res.render('student/RestSelect',{ 
          stu_name : req.session.user.stu_name,
          available_rest : req.session.user.available_rest,
          rest_result : result
        });
      }
    });
  }else
    res.render('index');
});
router.get('/MyInfo_Admin', function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    res.render('admin/MyInfo_Admin',{
      name:req.session.authId
    });
  }else
    res.render('index');
});
router.get('/MyInfo_student', function(req, res){
  if(req.session.authId){
    var sql="select * from hm_student where stu_number='"+req.session.user.stu_number+"'";
    db.select(sql,function(err,result){
      if (err) 
      console.log(err);
      else {
        result[0].stu_password="*****";
        res.render('student/MyInfo_student',{
          stu_name:result[0].stu_name,
          user:result[0]
        });
      }
    });
  }else
    res.render('index');
});
router.post('/RestCancle', urlencodedParser, function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    //console.log(req.body);
    db.query([
      "update hm_rest_table set verified=2 where rest_index="+req.body.restIndex
    ]);	
  }else
    res.render('index');
});
router.post('/RestConsent', urlencodedParser, function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    db.query_val([
      {
        sql:"update hm_rest_table set verified=1 where rest_index=(?)",
        values:[req.body.restIndex]
      },
      {
        sql:"update hm_student set available_rest=available_rest-1 where stu_number=(?)",
        values:[req.body.stu_number]
      },
      {
        sql:"update hm_student set used_rest=used_rest+1 where stu_number=(?)",
        values:[req.body.stu_number]
      }
    ]);
  }else
    res.render('index');
});
router.post('/StudentUpdate', urlencodedParser, function(req, res){
  if(req.session.authId){
    db.query_val([
      {
        sql:"update hm_student set stu_password=(?),stu_birthday=(?),stu_addr=(?),stu_phone=(?) where stu_number=(?)",
        values:[
          req.body.stu_password,
          req.body.stu_birthday,
          req.body.stu_addr,
          req.body.stu_phone,
          req.session.user.stu_number
        ]
      }
    ]);
    res.redirect('/');	
  }else
    res.render('index');
});
router.post('/AdminUpdate', urlencodedParser, function(req, res){
  if(req.session.authId&&(req.session.authId=='human')){
    db.query_val([
      {
        sql:"update hm_student set stu_password=(?) where stu_number=(?)",
        values:[
          req.body.stu_password,
          req.session.user.stu_number
        ]
      }
    ]);
    res.redirect('/');	
  }else
    res.render('index');
});
router.post('/login', urlencodedParser, function(req, res){
  var user={
    id:req.body.user_id,
    password:req.body.user_password
  };
  db.login_check(user,function(err, content) {
    if (err) 
        console.log(err);
    else {
      if(content){
        req.session.authId = req.body.user_id;
        req.session.user = content;
	      req.session.save();
      }else{
        console.log("아이디 와 비밀번호가 일치하지 않습니다. 또는 아이디가 존재하지 않습니다.");
      }
      res.redirect('/');		
    }
  });
});
router.post('/insertStudent', urlencodedParser, function(req, res){//휴가 신청 등록
  db.query_val([
    {
      sql:"insert into hm_student values(?,?,?,?,?,?,?,?,?)",
      values:[
        req.body.sub_name,
        req.body.stu_name,
        req.body.stu_number,
        req.body.stu_password,
        req.body.stu_birthday,
        req.body.stu_addr,
        req.body.phone1+"-"+req.body.phone2+"-"+req.body.phone3,
        10,
        0
      ]
    }
  ]);
  res.redirect('/'); 
});
router.post('/saveBase64', urlencodedParser, function(req, res){//휴가 신청 등록
  // string generated by canvas.toDataURL()
  var img = req.body.imgBase64;
  // Grab the extension to resolve any image error
  var ext = img.split(';')[0].match(/jpeg|png|gif/)[0];
  // strip off the data: url prefix to get just the base64-encoded bytes
  var data = img.replace(/^data:image\/\w+;base64,/, "");
  var buf = new Buffer(data, 'base64');
  var nowTime=new Date().toLocaleString().replace(/\s|\.|:/gi, "");
  var fileName=req.session.user.stu_number+"_"+nowTime+'.'+ ext;
  fs.writeFile('./public/SignImages/'+fileName, buf,(err) => err);
  db.query_val([
    {
      sql:"insert into hm_rest_table values(?,?,?,?,?,?,?,?,?,?,?)",
      values:[
        null,
        req.session.user.sub_name,
        req.session.user.stu_number,
        req.session.user.stu_name,
        req.session.user.used_rest,
        nowTime,
        req.body.rest_date,
        req.body.rest_reason,
        fileName,
        "대기중..",
        "0"
      ]
    }
  ]);
  res.redirect('/'); 
});
router.post('/RestCheck', urlencodedParser, function(req, res){
  if(req.session.authId){
    res.render('student/RestCheck',{ 
      stu_name : req.session.user.stu_name,
      rest_date: req.body.date,
      rest_reason: req.body.reason,
      sign_src : req.body.mySign_src,
    });
  }else
    res.render('index');
});
router.get('/logout', function(req, res){
	req.session.destroy(function(){
    req.session;
    }); 
    res.redirect('/'); 
});

router.get('/findId', function(req, res){
  //res.render('content');var mysql_dbc = require('./db/con')();
res.render('findId');

});

router.post('/findMyId', urlencodedParser,function(req, res){
  //res.render('content');var mysql_dbc = require('./db/con')();
res.render('findId');

});

router.get('*', function(req, res){//404 page
  //res.render('404');
});


//export this router to use in our index.js
module.exports = router;
