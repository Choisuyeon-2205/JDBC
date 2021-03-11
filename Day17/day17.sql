--부서정보 모두보기
select * from DEPARTMENTS;
--직원정보 모두보기
select * from EMPLOYEES; --HR

select * from emp; --SCOTT

create table kosta_Student(
	id number,
	name varchar2(30)
);

select * from tab;

desc kosta_Student;

select * from departments order by department_id desc;

select to_char(HIRE_DATE,'yyyy-mm-dd') from employees;

--null: 값이 없다.
select * from employees where department_id is null;

--모든 칼럼
select * from employees;
--특정 칼럼 나열
select employee_id, first_name from employees;
-- 특정 칼럼 나열.. 별명 부여
select employee_id 직원번호, first_name 성 from employees;

--연산식
select employee_id 직원번호, first_name || last_name 전체이름 from employees;
select employee_id 직원번호, first_name || last_name "전체 이름" from employees;
-- || concate 연산자

-- 자바: char=> 'A'  String=> "자바"
-- ORACLE: "" -> 칼럼이름 또는 테이블이름, 대소문자 구별한다.
--         '':값
select first_name from "employees"; --X
-- ORACLE dictionary table의 내용은 대문자이다.
select first_name from "EMPLOYEES"; --O
select first_name from eMployees; --O
-- " "을 붙이면 값으로 인식하여 대소문자 구분 함. 테이블 이름은 따옴표를 안쓰는게 좋음.

-- invalid identifier: 유효하지 않은 식별자(칼럼이름, 테이블이름)
select employee_id 직원번호, first_name||' '||last_name "전체 이름" from employees; --O
select employee_id 직원번호, first_name||" "||last_name "전체 이름" from employees; --X

--조건: first_name= Steven
select * 
from EMPLOYEES
where first_name = 'Steven';

--nvl(): null인지? null이면 두번째 인자 값으로 변경!
select salary , salary*0.1 세금, commission_pct, salary+salary*nvl(commission_pct,0) 수령액
from EMPLOYEES;

select job_id from employees; --107건
select distinct job_id from employees; --중복제거 => 19개

-- 작성 순서: select - from - where - orderby
-- 해석 순서: from - where - select - orderby
-- sudo
select rownum, first_name, hire_date, salary, department_id
from employees
where salary > 10000
and department_id <> 80
order by salary;
-- => 연봉이 5000 초과한 직원의 성, 입사일, 급여, 부서를 추출한다.(급여 오름차순으로 정렬)

select * from emp;

select EMPNO, ENAME, SAL
from emp
where ENAME = 'FORD';

select EMPNO, ENAME, SAL
from emp
where to_char(HIREDATE, 'RR/MM/DD') <= '82-01-01';


--default date format: RR/MM/DD
-- RR: 50 이상이면 1900년대 50미만이면 2000년대
select * from nls_session_parameters;

-- 설정 변경
--세션에서 설정 변경 => 한 번 보내고 끝나서 설정 유지x =>
alter session set nls_date_format= 'yyyy-mm-dd';

select * from nls_session_parameters where parameter= 'NLS_DATE_FORMAT';


select * from EMPLOYEES; --HR
--1
select employee_id, salary, department_id 
from employees
where salary > = 15000;
--2
select first_name||' '||last_name 이름, salary*12 연봉
from employees
where salary*12 >= 170000;
--3
select first_name||' '||last_name 이름, salary 급여
from employees
where department_id is null;
--4
select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where to_char(hire_date, 'yyyy-mm-dd') <= '2004-12-31';
