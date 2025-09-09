-- Dados de exemplo para Papelaria Pacheco Torres (idempotente)

-- Clientes
INSERT INTO clientes (id, nome, cpf, endereco, telefone, email, created_at) VALUES
(1, 'João Góes', '123.456.789-00', 'Rua das Flores, 123', '(11) 99999-9999', 'joao@email.com', '2024-01-15 10:30:00'),
(2, 'Pedro Arenas', '987.654.321-00', 'Av. Principal, 456', '(11) 88888-8888', 'maria@email.com', '2024-01-16 14:20:00'),
(3, 'Frederico', '987.655.321-00', 'Av. Principal, 456', '(11) 88881-8888', 'freed@email.com', '2024-01-16 14:20:00')
ON CONFLICT (id) DO NOTHING;

-- Produtos
INSERT INTO produtos (id, nome, codigo, preco, categoria, descricao, created_at) VALUES
(1, 'Caneta Azul', 'CAN001', 2.50, 'Escritório', 'Caneta esferográfica azul', '2024-01-10 09:15:00'),
(2, 'Caderno Universitário', 'CAD001', 15.90, 'Papelaria', 'Caderno 96 folhas', '2024-01-11 11:45:00'),
(3, 'Lápis HB', 'LAP001', 1.25, 'Escritório', 'Lápis grafite HB', '2024-01-12 16:30:00')
ON CONFLICT (id) DO NOTHING;

-- Estoque
INSERT INTO estoque (id, produto_id, quantidade, quantidade_minima, ultima_atualizacao) VALUES
(1, 1, 50, 10, '2024-01-15 08:00:00'),
(2, 2, 25, 5, '2024-01-16 10:15:00'),
(3, 3, 100, 20, '2024-01-17 07:30:00')
ON CONFLICT (id) DO NOTHING;

-- Vendas
INSERT INTO vendas (id, cliente_id, total, data) VALUES
(1, 1, 20.90, '2024-01-18 14:30:00'),
(2, 2, 6.25, '2024-01-19 16:45:00')
ON CONFLICT (id) DO NOTHING;

-- Itens de Venda (defina IDs para evitar duplicação)
INSERT INTO itens_venda (id, venda_id, produto_id, quantidade, preco_unitario) VALUES
(1, 1, 1, 2, 2.50),
(2, 1, 2, 1, 15.90),
(3, 2, 3, 5, 1.25)
ON CONFLICT (id) DO NOTHING;

-- Ajuste dos sequences (Serial) para o MAX(id) atual
SELECT setval('clientes_id_seq', COALESCE((SELECT MAX(id) FROM clientes), 1));
SELECT setval('produtos_id_seq', COALESCE((SELECT MAX(id) FROM produtos), 1));
SELECT setval('estoque_id_seq', COALESCE((SELECT MAX(id) FROM estoque), 1));
SELECT setval('vendas_id_seq', COALESCE((SELECT MAX(id) FROM vendas), 1));
SELECT setval('itens_venda_id_seq', COALESCE((SELECT MAX(id) FROM itens_venda), 1));
