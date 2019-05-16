
#ifndef REGISTERDIALOG_H
#define REGISTERDIALOG_H

#include <QDialog>

namespace Ui {
class registerDialog;
}

class registerDialog : public QDialog
{
    Q_OBJECT

public:
    explicit registerDialog(QWidget *parent = nullptr);
    ~registerDialog();

private:
    Ui::registerDialog *ui;

signals:
    void newRegister(QString, QString);
private slots:
    void on_pushButton_clicked();
};

#endif // REGISTERDIALOG_H
