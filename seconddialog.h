#ifndef SECONDDIALOG_H
#define SECONDDIALOG_H

#include <QDialog>
#include <QtNetwork/QNetworkAccessManager>
#include <QFile>

namespace Ui {
class secondDialog;
}

class secondDialog : public QDialog
{
    Q_OBJECT

public:
    explicit secondDialog(QWidget *parent = nullptr);
    ~secondDialog();

public slots:
    void pixMapa(QPixmap pix);
private slots:
    void on_pushButton_clicked();

private:
    Ui::secondDialog *ui;
    
};

#endif // SECONDDIALOG_H
