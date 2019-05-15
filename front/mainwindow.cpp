#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QString>
#include <QDebug>
#include "seconddialog.h"


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    connect(&regDialog, SIGNAL(newRegister(QString, QString)), this, SLOT(newRegister(QString, QString)));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    QNetworkAccessManager *man  = new QNetworkAccessManager(this);
    connect(man, &QNetworkAccessManager::finished, this,  &MainWindow::downloadFinished);
    const QUrl url = QUrl(myUrl);
    QNetworkRequest request(url);
    man->get(request);
}

void MainWindow::downloadFinished(QNetworkReply *reply){
    QPixmap pix;
    pix.loadFromData(reply->readAll());
    secondDialog sec;
    sec.setModal(true);
    sec.pixMapa(pix);
    sec.exec();
}

void MainWindow::on_lineEdit_editingFinished()
{

}

void MainWindow::on_pushButton_2_clicked()
{
    regDialog.setModal(true);
    regDialog.exec();
}

QString MainWindow::getUserPassword() const
{
    return userPassword;
}


QString MainWindow::getUserLogin() const
{
    return userLogin;
}

void MainWindow::newRegister(QString login, QString password)
{
    userLogin = login;
    userPassword = password;
}
