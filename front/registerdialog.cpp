#include "registerDialog.h"
#include "ui_registerDialog.h"
#include "mainwindow.h"

registerDialog::registerDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::registerDialog)
{
    ui->setupUi(this);
}

registerDialog::~registerDialog()
{
    delete ui;
}

void registerDialog::on_pushButton_clicked()
{
    if(ui->lineEdit_2->text() != "" && ui->lineEdit->text() != "")
        emit newRegister(ui->lineEdit->text(), ui->lineEdit_2->text());
    this->close();
}
