class DBC:
    import mysql.connector as con
    un = 'root'
    pss = 'Javafirst'
    hst = 'localhost'
    db = 'war_driving'
    connection = con.connect(user=un, password=pss, host=hst, database=db)

    def getConnection(self):
        return self.connection

class Point:
    def __init__(self, _id):
        self._id = _id

    def setXY(self, x, y):
        self.x = x
        self.y = y
    
    def setStrength(self, p1, p2, p3, p4, p5, p6, p7, p8):
        self.p1 = p1
        self.p2 = p2
        self.p3 = p3
        self.p4 = p4
        self.p5 = p5
        self.p6 = p6
        self.p7 = p7
        self.p8 = p8


if __name__=="__main__":
    dbc = DBC()
    connection = dbc.getConnection()
    print connection
