#ifndef SCREENDOWNLOAD_H
#define SCREENDOWNLOAD_H

#include <QDialog>

namespace Ui {
class screenDownload;
}

class screenDownload : public QDialog
{
    Q_OBJECT

public:
    explicit screenDownload(QWidget *parent = nullptr);
    ~screenDownload();

private:
    Ui::screenDownload *ui;
};

#endif // SCREENDOWNLOAD_H
