#ifndef SERVERTEST_H
#define SERVERTEST_H

#include <QObject>
#include <QDebug>
#include <QTcpServer>
#include <QTcpSocket>

class serverTest : public QObject
{
    Q_OBJECT
public:
    explicit serverTest(QObject *parent = nullptr);

signals:

public slots:
    void newConnection();

private:
    QTcpServer *server;
};

#endif // SERVERTEST_H
