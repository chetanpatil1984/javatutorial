#!/bin/sh

#rsync -hvuzr /scratch/chetpati/view_storage/chetpati_bip_main_raj/xdo /scratch/chetpati/chetan/work/rsync/chetpati_bip_main/
rsync -avz chetpati@adc00raj.us.oracle.com:/scratch/chetpati/chetan/work/rsync /cygdrive/d/chetan/work/
rsync -avz --exclude bin "/cygdrive/d/chetan/work/rsync/chetpati_bip_main/xdo" "/cygdrive/d/chetan/work/bipublisher/chetpati_bip_main_backup/"