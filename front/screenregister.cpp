#include "screenregister.h"
#include "ui_screenregister.h"
#include <QNetworkRequest>
#include <QNetworkReply>
#include <QJsonObject>
#include <QJsonDocument>
#include <QDebug>

screenRegister::screenRegister(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::screenRegister)
{
    ui->setupUi(this);
}

screenRegister::~screenRegister()
{
    delete ui;
}

void screenRegister::on_pushButton_clicked()
{
    if(ui->lineEdit_2->text() != "" && ui->lineEdit->text() != "")
        emit newRegister(ui->lineEdit->text(), ui->lineEdit_2->text());
    this->close();


    //connecting to url
    QNetworkAccessManager *man  = new QNetworkAccessManager(this);
    connect(man, &QNetworkAccessManager::finished, this,  &screenRegister::downloadFinished);
    QUrl url = QUrl(myUrl);
    QNetworkRequest request(url);

    //GETTING STUFF
    man->get(request);


    //SENDING JSON WITH REGISTER
    QJsonObject json;
    json.insert(QString("username"), ui->lineEdit->text());
    json.insert(QString("password"), ui->lineEdit_2->text());
    QJsonDocument jsonDoc(json);
    QByteArray jsonData= jsonDoc.toJson();
    request.setHeader(QNetworkRequest::ContentTypeHeader,"application/json");
    request.setHeader(QNetworkRequest::ContentLengthHeader,QByteArray::number(jsonData.size()));
    man->post(request, jsonData);


//    ui->setupUi(this);
//    QPixmap pix("C:/Users/Caio/Desktop/co.png");
//    ui->label->setPixmap(pix);
////    QPixmap pix;
////    pix.load("C:/Users/Caio/Desktop/co2.png");
////   /* pix.loadFromData("C://Users//Caio//Desktop//co2.png");*/
////    //qDebug() << pix.isNull();
////    ui->label->setPixmap(pix);
    ////    ui->label->setPixmap(pix.scaled(100,100,Qt::KeepAspectRatio));
}

void screenRegister::downloadFinished(QNetworkReply *reply)
{
    //receive string
    QString stringzona = reply->readAll();
    qDebug() << stringzona;

    //TODO
    //check if register is ok
    //then go to main screen.
}

void screenRegister::on_pushButton_2_clicked()
{
    this->close();
}
