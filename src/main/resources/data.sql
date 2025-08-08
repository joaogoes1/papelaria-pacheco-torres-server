-- Dados de exemplo para Papelaria Pacheco Torres

-- Inserindo Clientes
INSERT INTO clientes (id, nome, cpf, endereco, telefone, email, created_at) VALUES
(1, 'João Góes', '123.456.789-00', 'Rua das Flores, 123', '(11) 99999-9999', 'joao@email.com', '2024-01-15T10:30:00Z'),
(2, 'Pedro Arenas', '987.654.321-00', 'Av. Principal, 456', '(11) 88888-8888', 'maria@email.com', '2024-01-16T14:20:00Z'),
(3, 'Frederico', '987.655.321-00', 'Av. Principal, 456', '(11) 88881-8888', 'freed@email.com', '2024-01-16T14:20:00Z');

-- Inserindo Produtos
INSERT INTO produtos (id, nome, codigo, preco, categoria, descricao, created_at) VALUES
(1, 'Caneta Azul', 'CAN001', 2.50, 'Escritório', 'Caneta esferográfica azul', '2024-01-10T09:15:00Z'),
(2, 'Caderno Universitário', 'CAD001', 15.90, 'Papelaria', 'Caderno 96 folhas', '2024-01-11T11:45:00Z'),
(3, 'Lápis HB', 'LAP001', 1.25, 'Escritório', 'Lápis grafite HB', '2024-01-12T16:30:00Z');

-- Inserindo Estoque
INSERT INTO estoque (id, produto_id, quantidade, quantidade_minima, ultima_atualizacao) VALUES
(1, 1, 50, 10, '2024-01-15T08:00:00Z'),
(2, 2, 25, 5, '2024-01-16T10:15:00Z'),
(3, 3, 100, 20, '2024-01-17T07:30:00Z');

-- Inserindo Vendas
INSERT INTO vendas (id, cliente_id, total, data) VALUES
(1, 1, 20.90, '2024-01-18T14:30:00Z'),
(2, 2, 6.25, '2024-01-19T16:45:00Z');

-- Inserindo Itens de Venda
INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES
(1, 1, 2, 2.50),
(1, 2, 1, 15.90),
(2, 3, 5, 1.25);
