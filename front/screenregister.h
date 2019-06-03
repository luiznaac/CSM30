#ifndef SCREENREGISTER_H
#define SCREENREGISTER_H

#include <QDialog>
#include <QUrl>
#include <QNetworkAccessManager>

namespace Ui {
class screenRegister;
}

class screenRegister : public QDialog
{
    Q_OBJECT

public:
    explicit screenRegister(QWidget *parent = nullptr);
    ~screenRegister();

private:
    Ui::screenRegister *ui;
    QString myUrl = "http://192.168.0.18:8500/image/test";

signals:
    void newRegister(QString, QString);
private slots:
    void on_pushButton_clicked();
    void downloadFinished(QNetworkReply*);
    void on_pushButton_2_clicked();
};

#endif // SCREENREGISTER_H
