
Note to checker: Wherever there are variables it works for all kinds of Expressions(Sin, Neg, Mult...). its just easier and clearer to present it here as X/Y..

*Minus*

((x * 5) - (x * 2)) => (3 * x)
checked if left.left equals right.left and both rights are instance of Num- return Minus of nums mult by the var.

((x * 5) - (2 * x)) --> (3 * x)
checked if left.left equals right.right and left.right,right.left are instance of Num- return Minus of nums mult by the var.

((5 * x) - (2 * x)) --> (3 * x)
checked if left.right equals right.right and both lefts are instance of Num- return Minus of nums mult by the var.

((5 * x) - (x * 2)) --> (3 * x)
checked if left.right equals right.left and left.left,right.right are instance of Num- return Minus of nums mult by the var.

((-x) - (-y)) --> (y - x)
checked if left and right are Neg, returned exp of right Minus exp of left.

((-x) - x) --> ((-2) * x)
checked if left is neg and its exp equals to right, returned -2 Mult by right.

(x - (-x)) --> (2 * x)
checked if right is neg and its exp equals to left, returned 2 Mult by left.

((-x) - y) --> (-(x + y))
checked if left is neg and its exp doesn't equal to right, returned Neg of Plus of right and exp of left.

(x - (-y)) --> (x + y)
checked if right is neg and its exp doesn't equal to left, returned Plus of left and exp of right.


*Mult*

(x * x) --> (x^2.0))
created a method that checks if leftEqualsRight and if it is- returns left^2.

((x + y) * (y + x)) --> ((x + y)^2.0)
created a method that checks if leftEqualsRight and if it is- returns left^2.

(-x) * (-y) --> (x * y)
checked if left and right are Neg, returned Mult of Left and right.

x * (-y) --> -(x * y)
checked if right is Neg, returned Neg of Mult.

(-x) * y --> -(x * y)
checked if left is Neg, returned Neg of Mult.


*Neg*

(-(-x)) --> x
if exp instance of Neg- return exp in exp

-0 --> 0
if exp ==0 return 0



*Plus*

(x + x) --> (2.0 * x))
created a method that checks if leftEqualsRight and if it is- returns 2*left.

((x + y) + (y + x)) --> (2.0 * (x + y))
created a method that checks if leftEqualsRight and if it is- returns 2*left.

((x * 2) + (x * 5)) => (7 * x)
checked if left.left equals right.left and both rights are instance of Num- return sum of nums mult by the var.

((x * 2) + (5 * x)) --> (7 * x)
checked if left.left equals right.right and left.right,right.left are instance of Num- return sum of nums mult by the var.

((2 * x) + (5 * x)) --> (7 * x)
checked if left.right equals right.right and both lefts are instance of Num- return sum of nums mult by the var.

((2 * x) + (x * 5)) --> (7 * x)
checked if left.right equals right.left and left.left,right.right are instance of Num- return sum of nums mult by the var.

((-x) + (-y)) --> (-(x + y))
checked if left and right are Neg, returned the Neg of their sum.

(-x) + x --> 0
checked if left is neg and its Expression equals right, return 0.

x + (-x) --> 0
checked if left is neg and its Expression equals right, return 0.

((-x) + y) --> (y - x)
checked if left is neg and its Expression doesn't equals right(in else), return right Minus exp of left.

(x + (-y)) --> (x - y)
checked if right is neg and its Expression doesn't equals left(in else), return left Minus exp of right.




