#include "servertest.h"

serverTest::serverTest(QObject *parent) : QObject(parent)
{
    server = new QTcpServer();
    connect(server, SIGNAL(newConnection()), this, SLOT(newConnection()));

    if(!server->listen(QHostAddress::Any, 1234)){
        qDebug() << "server could not start.";
    }else {
        qDebug() << "Server started";
    }

}


void serverTest::newConnection()
{
    QTcpSocket *socket = server->nextPendingConnection();

    socket->write("Hello, client\r\n");
    socket->flush();

    socket->waitForBytesWritten(3000);

    //socket->close();
}
