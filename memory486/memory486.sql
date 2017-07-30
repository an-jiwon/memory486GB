
-- 관리자 테이블
CREATE TABLE memoryAdmin (
  adminID VARCHAR2(10) PRIMARY KEY,
  adminPass VARCHAR2(10) NOT NULL
  );
--관리자 아이디 생성  
insert into MEMORYADMIN values('admin','admin');

-- 회원 테이블
CREATE TABLE memoryMember (
  memberID VARCHAR2(10) PRIMARY KEY,
  memberPass VARCHAR2(10) NOT NULL,
  name VARCHAR2(10) NOT NULL,
  email VARCHAR2(40) NOT NULL,
  joindate DATE,
  m_online VARCHAR2(10),
  m_out VARCHAR2(10),
  layout VARCHAR2(10)
  );
  select * from MEMORYMEMBER;
  insert into memoryMember values('k1','k1','k1','k1@k.com',sysdate,'off','n',null,null);
  update memoryMember set m_pic=null where memberID='k1';
  alter table memoryMember add m_location varchar2(50);
  alter table memoryMember add m_pic varchar2(100);

-- 커플인증 테이블
CREATE TABLE memoryCouple (
  ID_A VARCHAR2(10) PRIMARY KEY,
  ID_B VARCHAR2(10),
  accept VARCHAR2(10),
  FOREIGN KEY (ID_A) REFERENCES memoryMember(memberID)
  );  
  
-- 사진 테이블
CREATE TABLE memoryPhoto (
  p_no NUMBER PRIMARY KEY,
  p_file VARCHAR2(100) NOT NULL,
  p_writer VARCHAR2(10),
  p_date DATE,
  p_delete VARCHAR2(10),
  FOREIGN KEY (p_writer) REFERENCES memoryMember(memberID)
);

-- 대표사진 테이블
CREATE TABLE memoryProfile (
	pro_no NUMBER PRIMARY KEY,
	pro_title VARCHAR2(100) NOT NULL,
	pro_writer VARCHAR2(10) NOT NULL,
	pro_divide VARCHAR2(10) NOT NULL,
	FOREIGN KEY (pro_writer) REFERENCES memoryMember(memberID)
);


-- 캘린더 테이블
CREATE TABLE memoryCalendar (
  c_no NUMBER PRIMARY KEY,
  c_writer VARCHAR2(10),
  c_layout VARCHAR2(10),
  c_content VARCHAR2(3000) NOT NULL,
  c_category VARCHAR2(100) NOT NULL,
  c_date DATE,
  FOREIGN KEY (c_writer) REFERENCES memoryMember(memberID)
  );
  alter table memoryCalendar rename column c_date to fromDate;
  alter table memoryCalendar add(toDate Date);
  
-- 다이어리 테이블
CREATE TABLE memoryDiary (
  d_no NUMBER PRIMARY KEY,
  d_writer VARCHAR2(10),
  d_date DATE,
  d_content VARCHAR2(3000) NOT NULL,
  d_file VARCHAR2(100),
  d_location VARCHAR2(50),
  d_emotion VARCHAR2(50),
  d_delete VARCHAR2(10),
  FOREIGN KEY (d_writer) REFERENCES memoryMember(memberID)
  );
  
-- 다이어리의 댓글 테이블
CREATE TABLE memoryReply (
  d_no NUMBER,
  r_no NUMBER,
  r_writer VARCHAR2(50),
  r_content VARCHAR2(1000) NOT NULL,
  r_date DATE,
  CONSTRAINT pk PRIMARY KEY (d_no, r_no), 
  FOREIGN KEY (d_no) REFERENCES memoryDiary(d_no)
  );
  
  -- 디데이 테이블
  CREATE TABLE memoryDday (
  	day_no NUMBER PRIMARY KEY,
  	day_title VARCHAR2(100),
  	day_date Date,
  	day_id VARCHAR2(10)
  );
  

  select * from memoryphoto;
  select * from memoryCalendar;
  select * from memorymember;
  select * from memorycouple;
  select * from MEMORYDIARY;
  select * from memoryDday;
  

  delete from memorycouple where ID_A='k1';
  update memorycouple set accept='y' where ID_A='k1';
  update memoryMember set m_out='n' where memberID='k1';
  update memorymember set layout='left' where memberID='k1';
  update memorymember set layout='right' where memberID='k2';
  
  insert into MEMORYmember values('k2','k2','최임시','k2@k2.com',sysdate,null,null,null,'서울',null); 
  insert into MEMORYmember values('l1','l1','김임시','l1@kl.com',sysdate,null,null,null,'부산',null);
  update memorymember set m_out = 'n', m_online='off' where memberID='k2' or memberID='l1';
 