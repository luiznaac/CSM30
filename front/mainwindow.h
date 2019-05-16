#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QString>
#include <QtNetwork/QNetworkAccessManager>
#include <QFile>
#include <QNetworkAccessManager> //make requests
#include <QNetworkReply> //to handle replies
#include <QPixmap> //to work with images
#include "registerDialog.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    QString opa;
    ~MainWindow();

    QString getUserLogin() const;

    QString getUserPassword() const;

public slots:
    void newRegister(QString login, QString password);
private slots:
    void on_pushButton_clicked();
    void downloadFinished(QNetworkReply*);
    void on_lineEdit_editingFinished();

    void on_pushButton_2_clicked();

private:
    Ui::MainWindow *ui;
    QString myUrl = "https://images.uncyc.org/pt/thumb/c/c3/Negao_da_picona.jpg/300px-Negao_da_picona.jpg";
    registerDialog regDialog;
    QString userLogin;
    QString userPassword;

};

#endif // MAINWINDOW_H
