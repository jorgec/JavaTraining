<!doctype HTML>
<html>
	<head>
		<title>A Basic Calculator</title>
	</head>
	<body>
		<form method="get" action="calculator">
			<p>
				<input type="text" name="n1" placeholder="n1">
				<select name="opr">
					<option value="add">+</option>
					<option value="subtract">-</option>
					<option value="multiply">*</option>
					<option value="divide">/</option>
				</select>
				<input type="text" name="n2" placeholder="n2">
				<button type="submit">Calculate</button>
			</p>
		</form>
	</body>
</html>