#!/bin/bash
find ~/miapp/logs -name "*.log" -mtime +7 -exec rm -f {} \;

# Respaldo diario a las 2 am
0 2 * * * ~/miapp/scripts/respaldo_bd.sh >> ~/miapp/logs/backup.log 2>&1

# Limpieza de logs cada domingo a las 3 am
0 3 * * 0 ~/miapp/scripts/limpieza_logs.sh >> ~/miapp/logs/limpieza.log 2>&1

crontab -l
