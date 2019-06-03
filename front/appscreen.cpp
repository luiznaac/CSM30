#include "appscreen.h"
#include "ui_appscreen.h"
#include <QFileDialog>
#include <QPixmap>
#include <QDebug>
#include <QBuffer>
#include <QNetworkAccessManager>
#include <QUrl>
#include <QNetworkRequest>
#include <QNetworkReply>
#include <QFile>

appScreen::appScreen(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::appScreen)
{
    ui->setupUi(this);
    row_counter = 0;
    row_incrementer = 0;
    gGain = "";
}

appScreen::~appScreen()
{
    delete ui;
}

void appScreen::on_pushButton_clicked()
{
    QString file_name = QFileDialog::getOpenFileName(this, "open a file", "C:://");
    qDebug() << file_name;
    sendImage(file_name);
}

void appScreen::on_pushButton_2_clicked()
{

}

void appScreen::on_pushButton_3_clicked()
{
    this->close();
}

void appScreen::sendImage(QString fileDirectory)
{
//    QUrl testUrl("http://app/test.php");
//    QNetworkAccessManager* mgr = new QNetworkAccessManager();
//    QNetworkRequest request(testUrl);
//    QHttpMultiPart *multiPart = new QHttpMultiPart(QHttpMultiPart::FormDataType);

//    QHttpPart textPart;
//     textPart.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant("form-data; name=\"text\""));
//     textPart.setBody("my text");

//    QHttpPart imagePart;
//    imagePart.setHeader(QNetworkRequest::ContentTypeHeader, QVariant("image/jpeg"));
//    imagePart.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant("form-data; name=\"image\""));
//    request.setRawHeader("Content-ID", "my@content.id");
//    //request.setRawHeader("User-Agent", "Mozilla/5.21");
//    QFile *file = new QFile("C:/Users/Caio/Desktop.jpg");
//    qDebug() << file->open(QIODevice::ReadOnly);
//    imagePart.setBodyDevice(file);
//    file->setParent(multiPart);
//    multiPart->append(textPart);
//    multiPart->append(imagePart);
//    QNetworkReply *reply = mgr->post(request,multiPart);


    QFile file_g(fileDirectory);
    if (!file_g.open(QIODevice::ReadOnly | QIODevice::Text))
        return;

    QTextStream in(&file_g);
    while (!in.atEnd()) {
        QString file_g = in.readLine();
        processLine(file_g);
    }

    qDebug() << gGain;
    QString iw = "jeses \n heses";
    qDebug() << iw;

    QFile file("out.txt");
    if (!file.open(QIODevice::WriteOnly | QIODevice::Text))
        return;

    QTextStream out(&file);
    out << gGain;

    //pixmap to bytearray
    QPixmap pix("C:/Users/Caio/Desktop/negao.jpg");
    QByteArray array;
    QBuffer buffer(&array);
    buffer.open(QIODevice::WriteOnly);
    pix.save(&buffer, "PNG");


    QString stringByteArray;

//    for (int i = 0; i < array.size(); ++i) {
//        qDebug() << array.at(i) << endl;
//        stringByteArray.append(array.at(i));
//    }


    //sending bytearray
    QNetworkAccessManager *man  = new QNetworkAccessManager(this);
    connect(man, &QNetworkAccessManager::finished, this,  &appScreen::downloadFinished);
    QUrl url = QUrl(myUrl);
    QNetworkRequest request(url);
//    request.setHeader(QNetworkRequest::ContentTypeHeader);
    man->post(request, array);

}

void appScreen::downloadFinished(QNetworkReply *reply){
//    //receive image
//    QPixmap pix;
//    pix.loadFromData(reply->readAll());

    //qDebug() << reply->rawHeader();
    //            header(QNetworkRequest::KnownHeaders);
//    reply->header(QNetworkRequest::CookieHeader);
//    qDebug() << reply->rawHeaderPairs();
    QList<QByteArray> headerList = reply->rawHeaderList();
    foreach(QByteArray head, headerList) {
        qDebug() << head << ":" << reply->rawHeader(head);
    }

    //receive string
    QString stringzona = reply->readAll();
    qDebug() << stringzona;

    //TODO
    //CHECK IF LOGIN IS OK
    //THEN GO TO APPSCREEN
}

void appScreen::processLine(QString line_g){
    if(row_counter % 794 == 0)
        row_incrementer++;
    double line_g_double = line_g.toDouble();
    line_g_double = line_g_double + row_incrementer;
    gGain.append(QString::number(line_g_double, 'g', 20));
    gGain.append("\n");
    row_counter++;
}

