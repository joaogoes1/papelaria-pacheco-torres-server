-- ============================================================================
-- DADOS MASSIVOS para Machine Learning - Papelaria Pacheco Torres
-- 365 dias de histórico de vendas com padrões realistas
-- ============================================================================

-- ============================================================================
-- 1. CLIENTES (20 clientes)
-- ============================================================================
INSERT INTO clientes (id, nome, cpf, endereco, telefone, email, created_at) VALUES
(1, 'João Silva', '123.456.789-00', 'Rua das Flores, 123', '(11) 99999-0001', 'joao.silva@email.com', '2024-01-01 10:00:00'),
(2, 'Maria Santos', '234.567.890-11', 'Av. Paulista, 456', '(11) 99999-0002', 'maria.santos@email.com', '2024-01-01 10:15:00'),
(3, 'Pedro Oliveira', '345.678.901-22', 'Rua Augusta, 789', '(11) 99999-0003', 'pedro.oliveira@email.com', '2024-01-01 10:30:00'),
(4, 'Ana Costa', '456.789.012-33', 'Av. Faria Lima, 321', '(11) 99999-0004', 'ana.costa@email.com', '2024-01-01 10:45:00'),
(5, 'Carlos Mendes', '567.890.123-44', 'Rua Oscar Freire, 654', '(11) 99999-0005', 'carlos.mendes@email.com', '2024-01-01 11:00:00'),
(6, 'Juliana Lima', '678.901.234-55', 'Av. Brigadeiro, 987', '(11) 99999-0006', 'juliana.lima@email.com', '2024-01-01 11:15:00'),
(7, 'Ricardo Alves', '789.012.345-66', 'Rua Consolação, 147', '(11) 99999-0007', 'ricardo.alves@email.com', '2024-01-01 11:30:00'),
(8, 'Fernanda Souza', '890.123.456-77', 'Av. Rebouças, 258', '(11) 99999-0008', 'fernanda.souza@email.com', '2024-01-01 11:45:00'),
(9, 'Bruno Martins', '901.234.567-88', 'Rua Haddock Lobo, 369', '(11) 99999-0009', 'bruno.martins@email.com', '2024-01-01 12:00:00'),
(10, 'Patricia Rocha', '012.345.678-99', 'Av. Europa, 741', '(11) 99999-0010', 'patricia.rocha@email.com', '2024-01-01 12:15:00'),
(11, 'Gustavo Ferreira', '111.222.333-44', 'Rua Teodoro, 852', '(11) 99999-0011', 'gustavo.ferreira@email.com', '2024-01-01 12:30:00'),
(12, 'Amanda Pereira', '222.333.444-55', 'Av. Ibirapuera, 963', '(11) 99999-0012', 'amanda.pereira@email.com', '2024-01-01 12:45:00'),
(13, 'Rodrigo Barbosa', '333.444.555-66', 'Rua Pamplona, 159', '(11) 99999-0013', 'rodrigo.barbosa@email.com', '2024-01-01 13:00:00'),
(14, 'Camila Dias', '444.555.666-77', 'Av. Angélica, 357', '(11) 99999-0014', 'camila.dias@email.com', '2024-01-01 13:15:00'),
(15, 'Felipe Cardoso', '555.666.777-88', 'Rua Bela Cintra, 753', '(11) 99999-0015', 'felipe.cardoso@email.com', '2024-01-01 13:30:00'),
(16, 'Larissa Moreira', '666.777.888-99', 'Av. São João, 951', '(11) 99999-0016', 'larissa.moreira@email.com', '2024-01-01 13:45:00'),
(17, 'Thiago Ribeiro', '777.888.999-00', 'Rua da República, 357', '(11) 99999-0017', 'thiago.ribeiro@email.com', '2024-01-01 14:00:00'),
(18, 'Beatriz Cunha', '888.999.000-11', 'Av. Tiradentes, 159', '(11) 99999-0018', 'beatriz.cunha@email.com', '2024-01-01 14:15:00'),
(19, 'Leonardo Pinto', '999.000.111-22', 'Rua 7 de Abril, 753', '(11) 99999-0019', 'leonardo.pinto@email.com', '2024-01-01 14:30:00'),
(20, 'Isabela Campos', '000.111.222-33', 'Av. São Luís, 951', '(11) 99999-0020', 'isabela.campos@email.com', '2024-01-01 14:45:00')
ON CONFLICT (id) DO NOTHING;

-- ============================================================================
-- 2. PRODUTOS (15 produtos)
-- ============================================================================
INSERT INTO produtos (id, nome, codigo, preco, categoria, descricao, created_at) VALUES
(1, 'Caneta Azul', 'CAN001', 2.50, 'Escritório', 'Caneta esferográfica azul', '2024-01-01 09:00:00'),
(2, 'Caneta Preta', 'CAN002', 2.50, 'Escritório', 'Caneta esferográfica preta', '2024-01-01 09:05:00'),
(3, 'Caneta Vermelha', 'CAN003', 2.50, 'Escritório', 'Caneta esferográfica vermelha', '2024-01-01 09:10:00'),
(4, 'Caderno Universitário', 'CAD001', 15.90, 'Papelaria', 'Caderno 96 folhas', '2024-01-01 09:15:00'),
(5, 'Caderno Espiral', 'CAD002', 18.50, 'Papelaria', 'Caderno espiral 200 folhas', '2024-01-01 09:20:00'),
(6, 'Lápis HB', 'LAP001', 1.25, 'Escritório', 'Lápis grafite HB', '2024-01-01 09:25:00'),
(7, 'Borracha Branca', 'BOR001', 1.00, 'Escritório', 'Borracha branca escolar', '2024-01-01 09:30:00'),
(8, 'Apontador', 'APO001', 1.50, 'Escritório', 'Apontador com depósito', '2024-01-01 09:35:00'),
(9, 'Régua 30cm', 'REG001', 3.00, 'Escritório', 'Régua transparente 30cm', '2024-01-01 09:40:00'),
(10, 'Tesoura', 'TES001', 8.90, 'Escritório', 'Tesoura escolar ponta redonda', '2024-01-01 09:45:00'),
(11, 'Cola Bastão', 'COL001', 4.50, 'Papelaria', 'Cola em bastão 20g', '2024-01-01 09:50:00'),
(12, 'Papel Sulfite A4', 'PAP001', 25.00, 'Papelaria', 'Resma 500 folhas A4', '2024-01-01 09:55:00'),
(13, 'Pasta Catálogo', 'PAS001', 12.00, 'Escritório', 'Pasta catálogo 100 plásticos', '2024-01-01 10:00:00'),
(14, 'Marca Texto Amarelo', 'MAR001', 3.50, 'Escritório', 'Marca texto fluorescente', '2024-01-01 10:05:00'),
(15, 'Post-it Amarelo', 'POS001', 6.00, 'Escritório', 'Bloco autoadesivo 100 folhas', '2024-01-01 10:10:00')
ON CONFLICT (id) DO NOTHING;

-- ============================================================================
-- 3. ESTOQUE (para todos os produtos)
-- ============================================================================
INSERT INTO estoque (id, produto_id, quantidade, quantidade_minima, ultima_atualizacao) VALUES
(1, 1, 500, 50, '2024-01-01 08:00:00'),
(2, 2, 500, 50, '2024-01-01 08:00:00'),
(3, 3, 300, 30, '2024-01-01 08:00:00'),
(4, 4, 200, 20, '2024-01-01 08:00:00'),
(5, 5, 150, 15, '2024-01-01 08:00:00'),
(6, 6, 800, 80, '2024-01-01 08:00:00'),
(7, 7, 600, 60, '2024-01-01 08:00:00'),
(8, 8, 400, 40, '2024-01-01 08:00:00'),
(9, 9, 300, 30, '2024-01-01 08:00:00'),
(10, 10, 150, 15, '2024-01-01 08:00:00'),
(11, 11, 250, 25, '2024-01-01 08:00:00'),
(12, 12, 100, 10, '2024-01-01 08:00:00'),
(13, 13, 80, 8, '2024-01-01 08:00:00'),
(14, 14, 400, 40, '2024-01-01 08:00:00'),
(15, 15, 350, 35, '2024-01-01 08:00:00')
ON CONFLICT (id) DO NOTHING;

-- ============================================================================
-- 4. VENDAS MASSIVAS - 365 DIAS COM PADRÕES REALISTAS
-- ============================================================================
-- Padrões aplicados:
-- - Segunda a Sexta: mais vendas que fim de semana
-- - Janeiro: Volta às aulas (pico)
-- - Fevereiro: Carnaval (queda)
-- - Março-Abril: Normal
-- - Maio: Dia das Mães (pico)
-- - Junho: Dia dos Namorados (pequeno pico)
-- - Julho: Férias (queda)
-- - Agosto: Normal
-- - Setembro: Volta às aulas (pico)
-- - Outubro: Dia das Crianças (pico)
-- - Novembro-Dezembro: Natal (mega pico)
-- ============================================================================

-- FUNÇÃO PARA GERAR VENDAS COM PADRÕES REALISTAS
DO $$
DECLARE
    v_day INTEGER;
    v_date TIMESTAMP;
    v_day_of_week INTEGER;
    v_month INTEGER;
    v_sales_count INTEGER;
    v_base_amount NUMERIC;
    v_random_factor NUMERIC;
    v_cliente_id INTEGER;
    v_produto_id INTEGER;
    v_quantidade INTEGER;
    v_preco NUMERIC;
    v_venda_total NUMERIC;
    v_venda_id INTEGER;
    v_item_count INTEGER;
BEGIN
    -- Gerar vendas para os últimos 365 dias
    FOR v_day IN 0..364 LOOP
        v_date := CURRENT_DATE - (v_day || ' days')::interval + (10 + (random() * 9))::int * interval '1 hour';
        v_day_of_week := EXTRACT(DOW FROM v_date); -- 0=Domingo, 6=Sábado
        v_month := EXTRACT(MONTH FROM v_date);

        -- Definir número de vendas por dia baseado em padrões
        v_sales_count := 3; -- Base mínima

        -- Aumentar vendas em dias úteis
        IF v_day_of_week BETWEEN 1 AND 5 THEN
            v_sales_count := v_sales_count + (2 + (random() * 5)::int);
        END IF;

        -- Padrões sazonais
        CASE
            WHEN v_month = 1 THEN -- Janeiro - Volta às aulas
                v_sales_count := v_sales_count + (3 + (random() * 7)::int);
            WHEN v_month = 2 THEN -- Fevereiro - Carnaval (queda)
                v_sales_count := GREATEST(2, v_sales_count - 2);
            WHEN v_month = 5 THEN -- Maio - Dia das Mães
                v_sales_count := v_sales_count + (2 + (random() * 4)::int);
            WHEN v_month = 6 THEN -- Junho - Dia dos Namorados
                v_sales_count := v_sales_count + (1 + (random() * 3)::int);
            WHEN v_month = 7 THEN -- Julho - Férias (queda)
                v_sales_count := GREATEST(2, v_sales_count - 3);
            WHEN v_month = 9 THEN -- Setembro - Volta às aulas
                v_sales_count := v_sales_count + (4 + (random() * 8)::int);
            WHEN v_month = 10 THEN -- Outubro - Dia das Crianças
                v_sales_count := v_sales_count + (3 + (random() * 6)::int);
            WHEN v_month IN (11, 12) THEN -- Novembro/Dezembro - Natal
                v_sales_count := v_sales_count + (5 + (random() * 10)::int);
            ELSE
                v_sales_count := v_sales_count;
        END CASE;

        -- Gerar vendas para o dia
        FOR i IN 1..v_sales_count LOOP
            v_cliente_id := 1 + (random() * 19)::int; -- Cliente aleatório (1-20)
            v_venda_total := 0;

            -- Inserir venda
            INSERT INTO vendas (cliente_id, total, data)
            VALUES (v_cliente_id, 0, v_date + (random() * 600)::int * interval '1 minute')
            RETURNING id INTO v_venda_id;

            -- Adicionar 1 a 5 itens por venda
            v_item_count := 1 + (random() * 4)::int;

            FOR j IN 1..v_item_count LOOP
                v_produto_id := 1 + (random() * 14)::int; -- Produto aleatório (1-15)
                v_quantidade := 1 + (random() * 5)::int; -- 1 a 6 unidades

                -- Buscar preço do produto
                SELECT preco INTO v_preco FROM produtos WHERE id = v_produto_id;

                -- Inserir item da venda
                INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco_unitario)
                VALUES (v_venda_id, v_produto_id, v_quantidade, v_preco);

                -- Somar ao total da venda
                v_venda_total := v_venda_total + (v_quantidade * v_preco);
            END LOOP;

            -- Atualizar total da venda
            UPDATE vendas SET total = v_venda_total WHERE id = v_venda_id;
        END LOOP;
    END LOOP;
END $$;

-- ============================================================================
-- 5. AJUSTAR SEQUENCES
-- ============================================================================
SELECT setval('clientes_id_seq', COALESCE((SELECT MAX(id) FROM clientes), 1));
SELECT setval('produtos_id_seq', COALESCE((SELECT MAX(id) FROM produtos), 1));
SELECT setval('estoque_id_seq', COALESCE((SELECT MAX(id) FROM estoque), 1));
SELECT setval('vendas_id_seq', COALESCE((SELECT MAX(id) FROM vendas), 1));
SELECT setval('itens_venda_id_seq', COALESCE((SELECT MAX(id) FROM itens_venda), 1));

-- ============================================================================
-- RESUMO DOS DADOS GERADOS
-- ============================================================================
-- Clientes: 20
-- Produtos: 15
-- Estoque: 15 registros
-- Vendas: ~2.000-3.000 vendas (365 dias com média 5-8 vendas/dia)
-- Itens de Venda: ~5.000-9.000 itens
--
-- Padrões implementados:
-- ✓ Sazonalidade mensal
-- ✓ Diferença entre dias úteis e fins de semana
-- ✓ Picos de vendas (volta às aulas, natal, etc)
-- ✓ Quedas sazonais (férias, carnaval)
-- ✓ Variação de quantidade de itens por venda
-- ✓ 365 dias de histórico completo
-- ============================================================================