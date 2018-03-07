-- 문제 1
select concat(first_name, ' ' , last_name) as '이름'
from employees
where emp_no=10944;

-- 문제 2
select concat(first_name, ' ' , last_name) as '이름',
		gender as '성별',
        hire_date as '입사일'
from employees
order by hire_date;

-- 문제 3
select gender, count(*)
from employees
group by gender;

-- 문제 4
select count(distinct emp_no)
from salaries
where to_date='9999-01-01';

-- 문제 5
select count(distinct dept_no)
from departments;

-- 문제 6
select count(*)
from dept_manager
where to_date='9999-01-01';

-- 문제 7
select dept_name
from departments
order by length(dept_name) desc;

-- 문제 8
select count(*)
from salaries
where from_date <= now() and to_date >= now() and salary >= 120000;

-- 문제 9
select distinct title
from titles
order by length(title) desc;

-- 문제 10
select count(*)
from titles
where from_date <= now() and to_date >= now() and lower(title)='engineer';

-- 문제 11
select title
from titles
where emp_no=13250
order by from_date asc;