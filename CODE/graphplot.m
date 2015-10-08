clear all
clc         %clear all in the command window
load A_9_3  %load the file(.mat) that including the degree of each nodes
A_9_3       %show all the data
b=A_9_3(:,2)      %get the second column which is the degree column
average=mean(b)   %calculate the mean
a=var(b);  %get the square of the standard deviation
median=median(b) % get the median
standarddeviation=sqrt(a) % get the standard deviation
sum=average+5*standarddeviation    %the threshold for the supernode
[r,c]=size(A_9_3);  %get the size of the input matrix
ccr=average/r        %the clusting coefficient of the random graph
lr=log2(r)/log2(average)  %the average shortest length of the random graph

bar(b,0.2)            %get the histogram
title('degree of each node')  %give the title
xlabel('number of nodes')     %give the representation of the x axis
ylabel('degree')              %give the representation of the y axis
%subplot(2,2,2)
figure(2)
hist(b)               %get the frequency distribution graph
title('degree distribution')
xlabel('degree')
ylabel('number of nodes having that degree')
%subplot(2,2,3)
figure(3)
hist(b,700)            %another frequency distribution graph
title('degree distribution')
xlabel('degree')
ylabel('number of nodes having that degree')

row_index = A_9_3(:,2) >= sum;       %get all the nodes whose degree greater than the setting threshold
supernode = A_9_3(row_index,:)

%c=find(b>=5)
% figure(1)
 %get the average
% a=var(b)  %get the square of the standard deviation
% median=median(b) % get the median
% standarddeviation=sqrt(a) % get the standard deviation
% sum=average+standarddeviation
% %[x,y,z]=find(Sheet1>=sum)
% 
% %subplot(2,2,1)
% 
