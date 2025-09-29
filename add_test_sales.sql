-- Adicionar 30 dias de vendas de teste para ML funcionar
INSERT INTO vendas (cliente_id, total, data)
SELECT
  (CASE WHEN gs % 3 = 0 THEN 1 WHEN gs % 3 = 1 THEN 2 ELSE 3 END) AS cliente_id,
  (150 + (RANDOM() * 200))::numeric(10,2) AS total,
  (CURRENT_DATE - (gs || ' days')::interval) AS data
FROM generate_series(1, 30) AS gs
ON CONFLICT DO NOTHING;