select rownum, first_name, last_name, salary, hire_date
from EMPLOYEES
where salary >= 10000 
and salary <= 20000
order by salary asc;

--연산자 우선순위 : NOT, AND, OR 
select rownum, first_name, last_name, salary, hire_date, commission_pct
from EMPLOYEES
where (salary between 10000 and 20000)
and (commission_pct = 0.3 or commission_pct = 0.2)
order by salary asc;

select *
from EMPLOYEES
where employee_id in (101, 105, 110);

-- 2005년이후 2007년 사이에 입사한 직원 조회
select *
from EMPLOYEES
where hire_date >= '2005-01-01' and hire_date <='2007-12-31';
--between
select *
from EMPLOYEES
where hire_date between '2005-01-01' and '2007-12-31';

select *
from EMPLOYEES
where department_id not in (100,60);

select *
from EMPLOYEES
where department_id <> 100 and department_id <> 60;

-- %: 0개 이상 아무문자나 온다.
-- _: 1개의 문자가 아무거나 가능하다.
select *
from EMPLOYEES
where first_name like 'S%';

select *
from EMPLOYEES
where first_name like '%s%';

select *
from EMPLOYEES
where first_name like '____';

select *
from EMPLOYEES
where length(first_name)=4;

select *
from EMPLOYEES
where first_name like '__s%';

select *
from EMPLOYEES
where first_name not like '%n';

select *
from EMPLOYEES
where manager_id is null;

sselect *
from EMPLOYEES
where commission_pct is not null;

select *
from jobs;

select *
from EMPLOYEES
order by commission_pct desc nulls last;

-- 실행 순서 1)from 2)where 3)select 4)order by
-- select에서 만든 tax변수는 order by에서는 가져다 쓸 수 있다.
select first_name, salary, department_id, hire_date, salary*0.1 tax
from EMPLOYEES
where department_id in (30,60,80)
order by tax desc, department_id asc, hire_date desc;

select first_name, salary, department_id, hire_date, salary*0.1 tax
from EMPLOYEES
where department_id in (30,60,80)
order by tax desc, 3 asc, hire_date desc;

select distinct 1+2, sysdate
from employees

﻿
select abs(-100),
		ceil(10.1) 무조건올림,
		floor(10.9) 무조건버림,
		round(10.5) 반올림,
		round(10.4) 반올림2,
		round(10.4567,2) 반올림3,
		trunc(10.4567,2) 내림,
		lower('HELLO') 소문자,
		upper('hello') 대문자,
		initcap('hello world') 첫문자를대문자로,
		'hello'||'코스타'||'가산역' 문자열연결,
		concat(concat('hello','코스타'),'가산역') 문자열연결2,
		substr('hello world',1,5) 일부문자추출,
		length('Hello World') 문자열길이,
		lengthb('Hello World') 문자열길이2,
		length('안녕하세요') 문자열길이3,
		lengthb('안녕하세요') 문자열길이4
from dual;

--한글 한글자가 3byte로 처리 => 5자 * 3 = 15바이트

﻿select *
from kosta_student

select first_name, length(first_name) 자릿수,
		substr(first_name,1,3) "3자리자르기",
		substr(first_name,2) "자르기",
		substr(first_name,-3,3) "3자리자르기",
		substr(first_name,-3) "뒤3자리자르기",
		substr(hire_date,1,2) 년도,
		substr(hire_date,4,2) 월2,
		to_char(hire_date, 'yyyy') 년도4자리,
		to_char(hire_date, 'mm') 월
from EMPLOYEES

-- 9월에 입사한 사원 조회
select *
from EMPLOYEES
where to_char(hire_date,'mm')='09';

select *
from EMPLOYEES
where substr(hire_date,4,2)='09';

select *
from EMPLOYEES
where hire_date like '__/09/__';

﻿﻿---- 소문자 s가 포함된 first_name을 가진 직원들 20건
select first_name
from EMPLOYEES
where instr(first_name, 's')>0;

select first_name
from EMPLOYEES
where first_name like '%s%';

select first_name,
		'*'||ltrim('   Oracle   ')||'*' "왼쪽공백지우기",
		'*'||rtrim('   Oracle   ')||'*' "오른쪽공백지우기",
		'*'||trim('   Oracle   ')||'*' "공백지우기"
from EMPLOYEES
﻿
select sysdate, sysdate-1 어제, sysdate+1 내일
from dual

select hire_date, 
round(months_between(sysdate, hire_date),2) 근무기간_월수,
round(months_between(sysdate, hire_date)/12) 근무기간_년수,
trunc(sysdate-hire_date) 입사일수,
last_day(hire_date) 월의마지막일
from EMPLOYEES

select add_months(sysdate,4), last_day(sysdate) 월의마지막일
from dual

select to_char(hire_date,'yyyy/mm/dd MON DAY DY hh:mi:ss') 문자로변경
from EMPLOYEES

-- 숫자 -> 문자 
select first_name, to_char(salary,'L999,999,999.99') 급여,
		to_char(0,'999,999')
from employees

select first_name,
		to_char(salary,'000,000,000,000') "0으로표현",
		to_char(salary,'999,999,999,999') "9로표현"
from employees


select hire_date
from employees
where hire_date >= to_date('20040101','mmddyyyy')

select first_name,
		nvl(to_char(manager_id),'사장님') 매니저id,
		nvl(commission_pct,0) 커미션,
		nvl(to_char(department_id),'부서없음') 부서,
		nvl2(department_id, to_char(department_id),'부서없음') 부서2,
		nullif(department_id,60) "60번부서인지",
	coalesce ( commission_pct, department_id, manager_id ) "null이아인처음값찾기"
from employees
where nullif(department_id,60) is null

-- decode: 자바의 3항 연산자, if
select first_name, department_id,
	decode(department_id,10,'개발부',20,'판매부',50,'마케팅','나머지') 조건에따른결과,
	case when department_id<=30 then '30보다작아'
		 when department_id<=50 then '50보다작아'
		 when department_id<=100 then '100보다작아'
		 else '기타' end "case연습"
from employees



hr/hr
========================================
		SELECT 기본
========================================

1. 급여가 15000 이상인 직원들의 이름, 급여, 부서id를 조회하시오.
select first_name||' '||last_name, salary, department_id 
from EMPLOYEES
where salary>=15000


2. 직원 중에서 연봉이 170000 이상인 직원들의 이름, 연봉을 조회하시오.
   연봉은 급여(salary)에 12를 곱한 값입니다.
select first_name||' '||last_name 이름, salary*12 연봉
from employees
where salary*12 >= 170000;


3. 직원 중에서 부서id가 없는 직원의 이름과 급여를 조회하시오.
select first_name||' '||last_name 이름, salary 급여
from employees
where department_id is null;


4. 2004년 이전에 입사한 직원의 이름, 급여, 입사일을 조회하시오.

--3. index가 있다면 조작하지 않는 것이 바람직하다. 함수를 이용해서 조작하면 index를 미사용
select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where hire_date <= '2004-12-31';

select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where substr(hire_date, 1, 2) <= '04';


select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where to_char(hire_date,'yyyy')<= 2004

-- 논리연산자 -- 
1. 80, 50 번 부서에 속해있으면서 급여가 13000 이상인 직원의 이름, 급여, 부서id
를 조회하시오.
-- 2건
select first_name||' '||last_name 이름, salary 급여, department_id 부서id
from employees
where department_id in (80,50) 
and salary >= 13000


2. 2005년 이후에 입사한 직원들 중에서 급여가 1300 이상 20000 이하인 직원들의 
이름, 급여, 부서id, 입사일을 조회하시오.
--83건
select first_name||' '||last_name 이름, salary 급여, department_id 부서id, hire_date 입사일
from employees
where hire_date >= '2005-01-01'
and (salary between 1300 and 20000);


3. 2005년도 입사한 직원의 정보만 출력하시오.
--29건
select *
from employees
where hire_date between '2005-01-01' and '2005-12-31'


4. 이름이 D로 시작하는 직원의 이름, 급여, 입사일을 조회하시오.
select first_name||' '||last_name 이름, salary 급여, department_id 부서id
from employees
where last_name like 'D%'


5. 12월에 입사한 직원의 이름, 급여, 입사일을 조회하시오.
select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where hire_date like '__/12/__'


6. 이름에 le 가 들어간 직원의 이름, 급여, 입사일을 조회하시오.
--4건
select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where last_name like '%le%'

7. 이름이 m으로 끝나는 직원의 이름, 급여, 입사일을 조회하시오.
--1건
select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where last_name like '%m'

8. 이름의 세번째 글자가 r인 이름, 급여, 입사일을 조회하시오.
--12건
select first_name||' '||last_name 이름, salary 급여, hire_date 입사일
from employees
where last_name like '__r%'


9. 커미션을 받는 직원의 이름, 커미션, 급여를 조회하시오.
--35건
select first_name||' '||last_name 이름, commission_pct, salary
from employees
where commission_pct is not null


10. 커미션을 받지 않는 직원의 이름, 커미션, 급여를 조회하시오.
select first_name||' '||last_name 이름, commission_pct, salary
from employees
where commission_pct is null


11. 2000년대에 입사해서 30, 50, 80 번 부서에 속해있으면서, 
급여를 5000 이상 17000 이하를 받는 직원을 조회하시오. 
단, 커미션을 받지 않는 직원들은 검색 대상에서 제외시키며, 먼저 입사한 직원이 
먼저 출력되어야 하며 입사일이 같은 경우 급여가 많은 직원이 먼저 출력되록 하시오.
-- 34건
select *
from employees
where commission_pct is not null
and (to_char(hire_date,'yyyy') between '2000' and '2009')
and department_id in (30,50,80)
and salary between 5000 and 17000
order by hire_date , salary desc


========================================
		단일행 함수
========================================

1. 이름이 'adam' 인 직원의 급여와 입사일을 조회하시오.
select first_name, salary, hire_date
from employees
where first_name = initCap('adam')

select first_name, salary, hire_date
from employees
where lower(first_name) = 'adam'

2. 나라 명이 'united states of america' 인 나라의 국가 코드를 조회하시오.
select country_id
from countries
where lower(country_name)= 'united states of america'


3. 'Adam의 입사일은 95/11/02 이고, 급여는 7000 입니다.' 이런 식으로 직원
정보를 조회하시오.
select initCap(first_name)||'의 입사일은 '||hire_date ||'이고, 급여는 '||salary|| '입니다.' 출력
from employees


4. 이름이 5글자 이하인 직원들의 이름, 급여, 입사일을 조회하시오.
select first_name, salary, hire_date
from employees
where length(first_name)<= 5;

5.2006년도에 입사한 직원의 이름, 입사일을 조회하시오.
select first_name, hire_date
from employees
where hire_date like '06/__/__'

select first_name, hire_date
from employees
where to_char(hire_date, 'yyyy') = '2006'


6. 15년 이상 장기 근속한 직원들의 이름, 입사일, 급여, 근무년차를 조회하시오.
--60
select first_name, hire_date, salary, 
(sysdate - hire_date)/365 일자계산,
months_between(sysdate,hire_date)/12 월계산
from employees
where months_between(sysdate,hire_date)/12 >= 15
