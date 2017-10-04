from sklearn.neural_network import MLPClassifier
import math
import helper

# standard example data: X = [[0., 0.], [1., 1.], [-1, -1], [2, 3]]
#                        y = [0, 1, 0, 1]

train_in, train_out_x, train_out_y, test_in, test_out_x, test_out_y = helper.getStandardData()


#neural network
#8 aps <=> size1 = 5, size2 = 18
size1 = 5
size2 = 18

clf_y = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(size1,size2), random_state=1)
clf_y.fit(train_in, train_out_y)
list_y = clf_y.predict(test_in)

#print "prediction: ", clf_y.predict(test_in)
#print "reality: ", test_out_y

clf_x = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(size1,size2), random_state=1)
clf_x.fit(train_in, train_out_x)
list_x = clf_x.predict(test_in)

#acc = 2.09m



'''
#svm
from sklearn import svm
clf_x = svm.SVC()
clf_x.fit(train_in, train_out_x)
list_x = clf_x.predict(test_in)

clf_y = svm.SVC()
clf_y.fit(train_in, train_out_y)
list_y = clf_y.predict(test_in)
'''
'''
#PCA
from sklearn.decomposition import PCA
pca = PCA(n_components = 1, svd_solver = 'full')
pca.fit(train_in)
print pca.score(test_in)
'''

'''
#Kmean
from sklearn.cluster import KMeans
kmeans = KMeans(n_clusters = 9, random_state = 0)
kmeans.fit(train_in, train_out_x)
list_x = kmeans.predict(test_in)
kmeans2 = KMeans( n_clusters = 9, random_state = 0)
kmeans2.fit(train_in, train_out_y)
list_y = kmeans.predict(test_in)
'''
'''
#naive bayes
from sklearn.naive_bayes import GaussianNB
gnb1 = GaussianNB()
list_x = gnb1.fit(train_in, train_out_x).predict(test_in)
gnb2 = GaussianNB()
list_y = gnb2.fit(train_in, train_out_y).predict(test_in)
#acc = 2.6m
'''

'''
#SVR
from sklearn.svm import SVR
svr1 = SVR(kernel = 'poly', C=1e4, gamma = 0.00001)
list_x = svr1.fit(train_in, train_out_x).predict(test_in)
svr2 = SVR(kernel = 'poly', C=1e4, gamma = 0.00001)
list_y = svr2.fit(train_in, train_out_y).predict(test_in)
#acc = 2.03m rbf c=1e4 gamma=0.00001
#acc = 1.97m when kernel = poly
'''
'''
#knn
from sklearn.neighbors import KNeighborsClassifier
neigh1 = KNeighborsClassifier(n_neighbors = 7)
list_x = neigh1.fit(train_in, train_out_x).predict(test_in)
neigh2 = KNeighborsClassifier(n_neighbors = 7)
list_y = neigh1.fit(train_in, train_out_y).predict(test_in)
#acc = 3.06
'''


#calculate accuracy
s = 0
for i in range(len(test_out_x)):
    acc = math.sqrt((test_out_x[i]-list_x[i])**2+(test_out_y[i]-list_y[i])**2)
    s  = s + acc

accuracy = s/len(test_out_x)
print "accuracy = ", accuracy

