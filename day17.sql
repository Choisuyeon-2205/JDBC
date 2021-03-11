--�μ����� ��κ���
select * from DEPARTMENTS;
--�������� ��κ���
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

--null: ���� ����.
select * from employees where department_id is null;

--��� Į��
select * from employees;
--Ư�� Į�� ����
select employee_id, first_name from employees;
-- Ư�� Į�� ����.. ���� �ο�
select employee_id ������ȣ, first_name �� from employees;

--�����
select employee_id ������ȣ, first_name || last_name ��ü�̸� from employees;
select employee_id ������ȣ, first_name || last_name "��ü �̸�" from employees;
-- || concate ������

-- �ڹ�: char=> 'A'  String=> "�ڹ�"
-- ORACLE: "" -> Į���̸� �Ǵ� ���̺��̸�, ��ҹ��� �����Ѵ�.
--         '':��
select first_name from "employees"; --X
-- ORACLE dictionary table�� ������ �빮���̴�.
select first_name from "EMPLOYEES"; --O
select first_name from eMployees; --O
-- " "�� ���̸� ������ �ν��Ͽ� ��ҹ��� ���� ��. ���̺� �̸��� ����ǥ�� �Ⱦ��°� ����.

-- invalid identifier: ��ȿ���� ���� �ĺ���(Į���̸�, ���̺��̸�)
select employee_id ������ȣ, first_name||' '||last_name "��ü �̸�" from employees; --O
select employee_id ������ȣ, first_name||" "||last_name "��ü �̸�" from employees; --X

--����: first_name= Steven
select * 
from EMPLOYEES
where first_name = 'Steven';

--nvl(): null����? null�̸� �ι�° ���� ������ ����!
select salary , salary*0.1 ����, commission_pct, salary+salary*nvl(commission_pct,0) ���ɾ�
from EMPLOYEES;

select job_id from employees; --107��
select distinct job_id from employees; --�ߺ����� => 19��

-- �ۼ� ����: select - from - where - orderby
-- �ؼ� ����: from - where - select - orderby
-- sudo
select rownum, first_name, hire_date, salary, department_id
from employees
where salary > 10000
and department_id <> 80
order by salary;
-- => ������ 5000 �ʰ��� ������ ��, �Ի���, �޿�, �μ��� �����Ѵ�.(�޿� ������������ ����)

select * from emp;

select EMPNO, ENAME, SAL
from emp
where ENAME = 'FORD';

select EMPNO, ENAME, SAL
from emp
where to_char(HIREDATE, 'RR/MM/DD') <= '82-01-01';


--default date format: RR/MM/DD
-- RR: 50 �̻��̸� 1900��� 50�̸��̸� 2000���
select * from nls_session_parameters;

-- ���� ����
--���ǿ��� ���� ���� => �� �� ������ ������ ���� ����x =>
alter session set nls_date_format= 'yyyy-mm-dd';

select * from nls_session_parameters where parameter= 'NLS_DATE_FORMAT';


select * from EMPLOYEES; --HR
--1
select employee_id, salary, department_id 
from employees
where salary > = 15000;
--2
select first_name||' '||last_name �̸�, salary*12 ����
from employees
where salary*12 >= 170000;
--3
select first_name||' '||last_name �̸�, salary �޿�
from employees
where department_id is null;
--4
select first_name||' '||last_name �̸�, salary �޿�, hire_date �Ի���
from employees
where to_char(hire_date, 'yyyy-mm-dd') <= '2004-12-31';
