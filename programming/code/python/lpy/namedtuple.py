from collections import namedtuple

Duck = namedtuple('Duck', 'bill tail')
duck = Duck('wide orange', 'long')

print(duck)
print(duck.bill)
print(duck.tail)


duck2 = Duck(**{'bill': 'wide orange', 'tail': 'long'})
print(duck2)

duck3 = duck2._replace(tail='magnificent', bill='crushing')
print(duck3)
print(duck2)

def unicode_test(value):
    import unicodedata
    name = unicodedata.name(value)
    value2 = unicodedata.lookup(name)
    print('value="%s", name="%s", value2="%s"' %(value, name, value2))

# unicode_test('\u2603')

str = "Hi {}, {} is a {}.".format("there", "Tom", "cat")
str1 = "Hi {1}, {2} is a {0}.".format("cat", "there", "Tom")
str2 = "Hi {n}, {s} is a {t}".format(n="there", s="Tom", t="cat")
str3 = ""
print(str)
print(str1)
print(str2)
