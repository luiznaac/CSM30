#ifndef APPSCREEN_H
#define APPSCREEN_H

#include <QDialog>
#include <QUrl>
#include <QNetworkReply>

namespace Ui {
class appScreen;
}

class appScreen : public QDialog
{
    Q_OBJECT

public:
    explicit appScreen(QWidget *parent = nullptr);
    ~appScreen();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void on_pushButton_3_clicked();

    void downloadFinished(QNetworkReply*);

private:
    QString myUrl = "http://192.168.0.18:8500/image/test";
    Ui::appScreen *ui;
    void sendImage(QString fileDirectory);
    void processLine(QString line_g);
    int row_counter;
    int row_incrementer = 0;
    QString gGain;
};

#endif // APPSCREEN_H
