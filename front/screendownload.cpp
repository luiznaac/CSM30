#include "screendownload.h"
#include "ui_screendownload.h"

screenDownload::screenDownload(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::screenDownload)
{
    ui->setupUi(this);
}

screenDownload::~screenDownload()
{
    delete ui;
}

