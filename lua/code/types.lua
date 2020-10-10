a = "one string"
b = string.gsub(a, "one", "another")    -- меняет части строки
print(a)    --> строка "one string"
print(b)    --> строка "another string"

print(#a)
print(#b)

a = '\41'
print(a)

page = [[
<html>
<head>
  <title>An HTML Page</title>
</head>
<body>
  <a href="http://www.lua.org">Lua</a>
</body>
</html>
]]

write(page)