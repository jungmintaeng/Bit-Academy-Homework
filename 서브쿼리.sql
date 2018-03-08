-- 문제 1
select count(*) 직원수
from employees e, salaries s
where e.emp_no=s.emp_no
and s.to_date='9999-01-01'
and s.salary > (
	select avg(s.salary)
    from salaries s
    where s.to_date='9999-01-01'
);

-- 문제 2
select e.emp_no 사번,
concat(e.first_name,' ',e.last_name) 이름,
d.dept_name 부서,
maxSalary 연봉
from employees e, departments d, dept_emp de, salaries s, (
	select sa.emp_no maxMan, maxSalary
	from salaries sa
	join dept_emp demp on sa.emp_no=demp.emp_no
    join (
		select de.dept_no dept_no, max(s.salary) maxSalary
		from salaries s, dept_emp de
		where s.emp_no=de.emp_no
		and s.to_date='9999-01-01'
		and de.to_date='9999-01-01'
		group by de.dept_no
    ) subMax on demp.dept_no=subMax.dept_no
	where sa.to_date='9999-01-01'
	and demp.to_date='9999-01-01'
	and salary= maxSalary
) m
where e.emp_no=de.emp_no
and e.emp_no=s.emp_no
and d.dept_no=de.dept_no
and e.emp_no=m.maxMan
and s.to_date='9999-01-01'
and de.to_date='9999-01-01'
order by maxSalary desc;

-- 문제 3
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, s.salary 연봉
from employees e, salaries s, dept_emp de,(
	select de.dept_no 부서번호, avg(sa.salary) 평균연봉
    from dept_emp de, salaries sa
    where de.emp_no=sa.emp_no
    and sa.to_date='9999-01-01'
    and de.to_date='9999-01-01'
    group by dept_no
) av
where e.emp_no=s.emp_no
and e.emp_no=de.emp_no
and de.dept_no=av.부서번호
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
and s.salary > av.평균연봉;

-- 문제 4
select e.emp_no 사번, concat(e.first_name,' ',e.last_name) 이름, 매니저이름,
d.dept_name 부서이름
from employees e, departments d, dept_emp de,
(
	select dman.dept_no, concat(first_name,' ',last_name) 매니저이름
    from employees emp, dept_manager dman, dept_emp demp
    where emp.emp_no=demp.emp_no
    and dman.emp_no=emp.emp_no
    and dman.dept_no=demp.dept_no
    and dman.to_date='9999-01-01'
    and demp.to_date='9999-01-01'
) dm
where e.emp_no=de.emp_no
and dm.dept_no=de.dept_no
and d.dept_no=de.dept_no
and de.to_date='9999-01-01';

-- 문제 5
select e.emp_no 사번,
	concat(e.first_name,' ',e.last_name) 이름,
	t.title 직책,
	s.salary 연봉
from dept_emp de, employees e, salaries s, titles t
where e.emp_no=de.emp_no
and s.emp_no=e.emp_no
and t.emp_no=e.emp_no
and de.to_date='9999-01-01'
and t.to_date='9999-01-01'
and s.to_date='9999-01-01'
and de.dept_no=(
	select demp.dept_no
    from salaries sa, dept_emp demp
	where sa.emp_no=demp.emp_no
	and sa.to_date='9999-01-01'
	and demp.to_date='9999-01-01'
    group by demp.dept_no
    having round(avg(sa.salary))=(
		select max(avg_)
        from (
			select round(avg(salary)) avg_
			from salaries s, dept_emp de
			where s.emp_no=de.emp_no
			and s.to_date='9999-01-01'
			and de.to_date='9999-01-01'
			group by de.dept_no
        ) maxdept
    )
)
order by s.salary desc;

-- 문제 6
select d.dept_name
from departments d, dept_emp de, salaries s
where d.dept_no=de.dept_no
and de.emp_no=s.emp_no
group by de.dept_no
having round(avg(s.salary)) = (
	select round(max(avg_))
    from (
		select avg(salary) avg_
        from salaries s, dept_emp de
        where s.emp_no=de.emp_no
        group by dept_no
    ) a
);

-- 문제 7
select t.title
from titles t, salaries s
where t.emp_no=s.emp_no
group by t.title
having round(avg(s.salary))=(
	select round(max(avg_))
    from (
		select avg(salary) avg_
        from salaries s, titles t
        where s.emp_no=t.emp_no
        group by t.title
    ) a
);

-- 문제 8
select d.dept_name 부서이름, concat(e.first_name,' ',e.last_name) 이름,
s.salary 연봉, 매니저이름, 매니저연봉
from dept_emp de, departments d, employees e, salaries s, (
	select
		dm.dept_no 부서번호,
		concat(first_name,' ',last_name) 매니저이름,
		sa.salary 매니저연봉
	from dept_manager dm , salaries sa, employees emp
    where dm.emp_no=emp.emp_no
    and sa.emp_no=emp.emp_no
    and dm.to_date='9999-01-01'
    and sa.to_date='9999-01-01'
) man
where d.dept_no=de.dept_no
and e.emp_no=s.emp_no
and e.emp_no=de.emp_no
and d.dept_no=man.부서번호
and s.salary > 매니저연봉
and de.to_date='9999-01-01'
and s.to_date='9999-01-01';