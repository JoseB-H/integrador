#!/bin/bash

# Fecha con formato YYYYMMDD_HHMM
fecha=$(date +%Y%m%d_%H%M)

# Directorio donde guardar el respaldo
directorio=~/miapp/backups

# Asegura que la carpeta exista
mkdir -p "$directorio"

# Realiza el respaldo con mysqldump
mysqldump -u root -pjose159 FinalIntegrador > "$directorio/backup_$fecha.sql"

# Mensaje opcional
echo "✅ Respaldo realizado: backup_$fecha.sql"

chmod +x ~/miapp/scripts/respaldo_bd.sh
