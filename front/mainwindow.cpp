#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QString>
#include <QDebug>
#include <QByteArray>
#include <QBuffer>
#include <QIODevice>
#include <QUrlQuery>
#include <QUrl>
#include <QHttpMultiPart>
#include <QJsonObject>
#include <QJsonDocument>
#include <QPixmap>
#include "screenregister.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    connect(&reg, SIGNAL(newRegister(QString, QString)), this, SLOT(newRegister(QString, QString)));
    connect(&reg, SIGNAL(newRegister(QString, QString)), this, SLOT(newRegister(QString, QString)));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
//    //connecting to url
//    QNetworkAccessManager *man  = new QNetworkAccessManager(this);
//    connect(man, &QNetworkAccessManager::finished, this,  &MainWindow::downloadFinished);
//    QUrl url = QUrl(myUrl);
//    QNetworkRequest request(url);

//    //GETTING STUFF
//    man->get(request);


//    //SENDING JSON WITH LOGIN AND PASSWORD
//    QJsonObject json;
//    json.insert(QString("username"), ui->lineEdit->text());
//    json.insert(QString("password"), ui->lineEdit_2->text());
//    QJsonDocument jsonDoc(json);
//    QByteArray jsonData= jsonDoc.toJson();
//    request.setHeader(QNetworkRequest::ContentTypeHeader,"application/json");
//    request.setHeader(QNetworkRequest::ContentLengthHeader,QByteArray::number(jsonData.size()));
//    man->post(request, jsonData);


    //THRASH

//    request.setAttribute(QNetworkRequest::User, "username");
//    request.setUrl(url);
    //    request.setAttribute(QNetworkRequest::UserMax, "cassiooooooo");
    //request.setRawHeader(QByteArray("username"), QByteArray("cassio"));
//    request.setUrl(url);
//    man->post();
//            man->get(request);
//    man->post(request, query.query().toUtf8());

    app.setModal(true);
    app.exec();

}

void MainWindow::downloadFinished(QNetworkReply *reply){
    //receive image
    QPixmap pix;
    pix.loadFromData(reply->readAll());


    //receive string
    QString stringzona = reply->readAll();
    qDebug() << stringzona;

    //TODO
    //CHECK IF LOGIN IS OK
    //THEN GO TO APPSCREEN
}

void MainWindow::on_lineEdit_editingFinished()
{

}

void MainWindow::on_pushButton_2_clicked()
{
//    sendImage();
//    regDialog.setModal(true);
//    regDialog.exec();
//    QFile file("C:/Users/Caio/Desktop/co.png");
//    if (!file.open(QFile::ReadOnly))
//        return;
//    QByteArray array = file.readAll();
//    qDebug() << array.isEmpty();
//    QPixmap pixmap;
//    QBuffer buffer(&array);
//    buffer.open(QIODevice::WriteOnly);
//    pixmap.save(&buffer, "JPG");
//    qDebug() << pixmap.loadFromData(array, "PNG");
//    QPixmap pixmap("C:/Users/Caio/Desktop/co.png");

//    registerScreen reg;
//    reg.setModal(true);
//    reg.exec();

    reg.setModal(true);
    reg.exec();

}

void MainWindow::pixMapToByteArray(QPixmap pixmap)
{
    //sendImage();
//    QByteArray ba;              // Construct a QByteArray object
//    QBuffer buffer(&ba);        // Construct a QBuffer object using the QbyteArray
//    pixmap.save(&buffer, "PNG"); // Save the QImage data into the QBuffer
//    socket->write(ba);          // Send the QBuffer (QbyteArray) over a socket

//    // Preparation of our QPixmap


//    QByteArray bArray;
//    QBuffer buffer(&bArray);
//    buffer.open(QIODevice::WriteOnly);
//    pixmap.save(&buffer, "PNG");


//    qDebug() << bArray.isEmpty();
//    QString stringByteArray;

//    for (int i = 0; i < bArray.size(); ++i) {
//            qDebug() << bArray.at(i) << ",";
///*        stringByteArray.append(bArray.at(i));
//        stringByteArray.append(",")*/;
//    }
////    qDebug() << bArray.at(0);
//    qDebug() << bArray.toHex(); // "0a0b0c0d0e0f07020102"
//    char *data = bArray.data();
//    while (*data) {
//        qDebug() << "[" << *data << "]" << endl;
//        ++data;
//    }
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
