#include "mainwindow.h"
#include <QApplication>
#include "servertest.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
//    serverTest servert;
    MainWindow w;
    w.show();
    return a.exec();
}
