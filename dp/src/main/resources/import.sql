-- Inserir Departamentos
INSERT INTO tb_department (name) VALUES ('Desenvolvimento');
INSERT INTO tb_department (name) VALUES ('Design');
INSERT INTO tb_department (name) VALUES ('Recursos Humanos');
INSERT INTO tb_department (name) VALUES ('Financeiro');

-- Inserir Funcionários
INSERT INTO tb_employee (name) VALUES ('Vinícios Lima');
INSERT INTO tb_employee (name) VALUES ('Maria Silva');
INSERT INTO tb_employee (name) VALUES ('Alex Brown');
INSERT INTO tb_employee (name) VALUES ('Joana Green');

-- Inserir Associações (Mesa de Junção: tb_employee_department)
-- Vinícios está em Desenvolvimento (1) e Design (2)
INSERT INTO tb_employee_department (employee_id, department_id) VALUES (1, 1);
INSERT INTO tb_employee_department (employee_id, department_id) VALUES (1, 2);

-- Maria está em Desenvolvimento (1)
INSERT INTO tb_employee_department (employee_id, department_id) VALUES (2, 1);

-- Alex está em RH (3) e Financeiro (4)
INSERT INTO tb_employee_department (employee_id, department_id) VALUES (3, 3);
INSERT INTO tb_employee_department (employee_id, department_id) VALUES (3, 4);

-- Joana está em Design (2)
INSERT INTO tb_employee_department (employee_id, department_id) VALUES (4, 2);