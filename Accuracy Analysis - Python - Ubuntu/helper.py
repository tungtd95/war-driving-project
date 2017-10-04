#get data from database and split to 3 field: listTest, listTrain, listTrainer
def getData():
	from obj import DBC, Point
	#init variable
	listPoint = []

	index_test = [5, 8, 13, 20, 25, 30, 35, 40, 46, 53, 58, 64, 69, 72, 80] #points are used for testing
	index_train = [] #no. of trainer
	
	listTest = [] 	#save test point
	listTrain = []	#save train point

	#connect to database and collect data from db
	dbc = DBC()
	connection = dbc.getConnection()
	cursor = connection.cursor()
	cursor.execute("select * from ap_data_survey")

	#convert data from database to data training
	index = 0
	for _id1, x, y, p1, p2, p3, p4, p5, p6, p7, p8 in cursor:
		p = Point(index)
		p.setXY(x,y)
		p.setStrength(p1,p2,p3,p4,p5,p6,p7,p8)
		if index in index_test:
			listTest.append(p)
		else:
			listTrain.append(p)
		index = index + 1
	connection.close()
	cursor.close()
	return listTest, listTrain

#convert raw data to standard data
def getStandardData():
    listTest, listTrain = getData()
    train_in = []
    train_out_x = []
    train_out_y = []
    test_in = []
    test_out_x = []
    test_out_y = []

    train_in, train_out_x, train_out_y = createInOut(listTrain)
    test_in, test_out_x, test_out_y = createInOut(listTest)
        
    return train_in, train_out_x, train_out_y, test_in, test_out_x, test_out_y

def createInOut(listPoint):
    test_in1 = []
    test_out_x = []
    test_out_y = []
    for i in listPoint:
        test_in = []

        test_in.append(i.p1)
        test_in.append(i.p2)
        test_in.append(i.p3)
        test_in.append(i.p4)
        test_in.append(i.p5)
        test_in.append(i.p6)
        test_in.append(i.p7)
        test_in.append(i.p8)

        test_in1.append(test_in)
        test_out_x.append(i.x)
        test_out_y.append(i.y)

    return test_in1, test_out_x, test_out_y

if __name__ == "__main__":
    l1, l2 = getData()
    print len(l1), len(l2)
