DROP TABLE free_board;
drop sequence seq_free_board;

create sequence seq_free_board;

CREATE TABLE free_board (
   bo_num    NUMBER NOT NULL, 
   bo_title  VARCHAR2(250) NOT NULL,
   bo_category  char(4) , 
   bo_writer VARCHAR2(100) NOT NULL,
   bo_pass   varchar2(60)  not null, 
   bo_content clob,
   bo_ip     varchar2(30),
   bo_hit     number,   
   bo_reg_date date default sysdate,
   bo_mod_date date ,
   bo_del_yn  char(1) default 'N',
   constraint pk_free_board primary key(bo_num)
);

COMMENT ON COLUMN free_board.bo_num IS '글번호';
COMMENT ON COLUMN free_board.bo_title IS '글제목';
COMMENT ON COLUMN free_board.bo_category IS '글분류';
COMMENT ON TABLE free_board  IS '자유게시판';



commit
INSERT INTO free_board (
    bo_num, bo_title, bo_category,
    bo_writer, bo_pass, bo_content,
    bo_ip, bo_hit
) VALUES (
SELECT seq_free_board.nextval, '질문'||mem_name, 'BC0'||ceil(dbms_random.value(0,3)),
    mem_name, mem_pass,
    mem_name||''||mem_add1,
    '192.168.20.'||ceil(dbms_random.value(0,255)) as ip,
    ceil(dbms_random.value(0,1000)) as hit
FROM member)





Insert into COMM_CODE (COMM_CD,COMM_NM,COMM_PARENT,COMM_ORD) values ('BC00','글분류',null,0);
Insert into COMM_CODE (COMM_CD,COMM_NM,COMM_PARENT,COMM_ORD) values ('BC01','프로그램','BC00',1);
Insert into COMM_CODE (COMM_CD,COMM_NM,COMM_PARENT,COMM_ORD) values ('BC02','웹','BC00',2);
Insert into COMM_CODE (COMM_CD,COMM_NM,COMM_PARENT,COMM_ORD) values ('BC03','사는 이야기','BC00',3);
Insert into COMM_CODE (COMM_CD,COMM_NM,COMM_PARENT,COMM_ORD) values ('BC04','취업','BC00',4);

commit;

 select	 a.bo_num,
         a.bo_category, 
         b.comm_nm as cat_nm,
         a.bo_title,
         a.bo_writer,
         a.bo_content,
         to_char(a.bo_reg_date,'YYYY.MM.DD') as reg_data,
         a.bo_hit
        FROM free_board a,comm_code b	
        where a.bo_category = b.comm_cd	
        and bo_del_yn = 'N' 
        ORDER BY bo_num ASC ;
			
            
            
            select * from comm_code


select	 a.bo_num,                                         
  a.bo_category,                                           
  b.comm_nm as cat_nm,                                     
  a.bo_title,                                              
  a.bo_writer,                                             
  a.bo_content,                                            
  a.bo_ip,                                                 
  to_char(a.bo_reg_date,'YYYY.MM.DD HH:MM') as reg_date,   
  to_char(a.bo_mod_date,'YYYY.MM.DD HH:MM') as mod_date,   
  a.bo_hit                                                 
 FROM free_board a, comm_code b	               
 where a.bo_category = b.comm_cd	                       
 and bo_del_yn = 'N'                                       
 --and bo_num = 94      
 
 
 
 
 INSERT INTO free_board (
      bo_num , bo_title , bo_category
    , bo_writer, bo_pass, bo_content
    , bo_ip, bo_hit, bo_reg_date
    , bo_del_yn
) VALUES (
     seq_free_board.nextval , ? , ?
   , ? , ? , ?
   , ? , 0 , sysdate
   , 'N'
)
