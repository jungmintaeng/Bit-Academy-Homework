-- 문제 1
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, s.salary 연봉
from employees e, salaries s
where e.emp_no = s.emp_no
and s.to_date = '9999-01-01'
order by 연봉 desc;


-- 문제 2
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, t.title 직책
from employees e, titles t
where e.emp_no=t.emp_no
and t.to_date='9999-01-01'
order by 이름;

-- 문제 3
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, d.dept_name 부서
from employees e, departments d, dept_emp de
where e.emp_no=de.emp_no and d.dept_no=de.dept_no
and de.to_date='9999-01-01'
order by 이름;

-- 문제 4
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, s.salary 연봉,
t.title 직책, d.dept_name 부서
from employees e, dept_emp de, departments d, titles t, salaries s
where e.emp_no=de.emp_no
and de.dept_no=d.dept_no
and e.emp_no=t.emp_no
and e.emp_no=s.emp_no
and de.to_date='9999-01-01'
and t.to_date='9999-01-01'
and s.to_date='9999-01-01'
order by 이름;

-- 문제 5
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름
from employees e, titles t
where e.emp_no=t.emp_no
and t.title='Technique Leader'
and t.to_date!='9999-01-01';

-- 문제 6
select concat(e.first_name,' ',e.last_name) 이름,
	d.dept_name 부서명,
    t.title 직책
from employees e, departments d, dept_emp de, titles t
where e.emp_no=de.emp_no
and e.emp_no=t.emp_no
and d.dept_no=de.dept_no
and de.to_date='9999-01-01'
and t.to_date='9999-01-01'
and e.last_name like 'S%';

-- 문제 7
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, s.salary 급여
from employees e, titles t,salaries s
where e.emp_no=t.emp_no and e.emp_no=s.emp_no 
and t.to_date='9999-01-01'
and s.to_date='9999-01-01'
and t.title='Engineer'
and s.salary >= 40000
order by 급여 desc;

-- 문제 8
select t.title 직책, avg(s.salary) 연봉
from titles t, salaries s
where t.emp_no=s.emp_no
and t.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by t.title
order by 연봉 desc;

-- 문제 9
select d.dept_name 부서, avg(s.salary) 연봉
from departments d, dept_emp de, salaries s
where d.dept_no=de.dept_no
and de.emp_no=s.emp_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by de.dept_no
order by 연봉 desc;

-- 문제 10
select t.title 직책, avg(s.salary) 평균연봉
from titles t, salaries s
where t.emp_no=s.emp_no
and t.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by t.title
order by 평균연봉 desc;