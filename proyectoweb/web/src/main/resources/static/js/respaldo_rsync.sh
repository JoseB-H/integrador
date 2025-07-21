#!/bin/bash

fecha=$(date +%Y%m%d_%H%M)
destino="backups/rsync_copia_$fecha"

mkdir -p "$destino"

# Respaldar imágenes de productos
rsync -av --progress src/main/resources/static/img/producto/ "$destino/img/"

# Respaldar archivos de boleta (HTML u otros)
rsync -av --progress vistas/ "$destino/vistas/"

# Respaldar archivos .sql (si los generas con mysqldump)
rsync -av --progress backups/ "$destino/sql_backups/"

echo "✅ Copia completada en: $destino"

30 1 * * * /ruta/a/tu/proyecto/scripts/respaldo_rsync.sh >> /ruta/a/tu/proyecto/logs/rsync.log 2>&1
