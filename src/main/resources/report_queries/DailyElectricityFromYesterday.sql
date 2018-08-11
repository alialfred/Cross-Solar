SELECT CONVERT(he.reading_at, DATE) AS `date`, 
	SUM(he.generated_electricity) AS `sum`,
	AVG(he.generated_electricity) AS `average`,
	MIN(he.generated_electricity) AS `min`,
	MAX(he.generated_electricity) AS `max`
FROM panel p
LEFT OUTER JOIN hourly_electricity he ON he.panel_id = p.id
WHERE p.serial = :serial AND CONVERT(he.reading_at, DATE) < CURRENT_DATE
GROUP BY CONVERT(he.reading_at, DATE)

