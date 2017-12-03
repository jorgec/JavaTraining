<%@ include file="common/header.jsp"%>
<div class="container">
	<form method="post" action="order">
		<fieldset>
			<legend>Order Form</legend>
			<div class="form-group">
				<label for="first_name">First Name</label> <input type="email"
					class="form-control" id="first_name" name="first_name"
					placeholder="Juan" required>
			</div>
			<div class="form-group">
				<label for="last_name">Last Name</label> <input type="text"
					class="form-control" id="last_name" name="last_name"
					placeholder="Dela Cruz" required>
			</div>
			<div class="form-check">
				<!-- Multiple Radios -->
				<label class="control-label" for="gender">Gender</label> <label
					for="gender-0" class="form-check-label"> <input
					type="radio" name="gender" id="gender-0" value="male"
					checked="checked"> Male
				</label> <label for="gender-1" class="form-check-label"> <input
					type="radio" name="gender" id="gender-1" value="female">
					Female
				</label>
			</div>


			<div class="form-group">
				<label class="control-label" for="ageGroup">Age Group</label> <select
					id="ageGroup" name="ageGroup" class="form-control" required>
					<option></option>
					<option value="0">12 and below</option>
					<option value="1">13 to 17</option>
					<option value="2">18 to 25</option>
					<option value="3">26 to 35</option>
					<option value="4">36 to 40</option>
					<option value="5">41 to 55</option>
					<option value="6">56 to 65</option>
					<option value="7">66 and up</option>
				</select>
			</div>

			<div class="form-check">
				<label class="control-label" for="addons">Add Ons</label><br> <label
					for="addons-0" class="form-check-label"> <input
					type="checkbox" name="addons" id="addons-0" value="addon1"
					class="form-check-input"> Addon 1
				</label> <label for="addons-1" class="form-check-label"> <input
					type="checkbox" name="addons" id="addons-1" value="addon2"
					class="form-check-input"> Addon 2
				</label> <label for="addons-2" class="form-check-label"> <input
					type="checkbox" name="addons" id="addons-2" value="addon3"
					class="form-check-input"> Addon 3
				</label> <label for="addons-3" class="form-check-label"> <input
					type="checkbox" name="addons" id="addons-3" value="addon4"
					class="form-check-input"> Addon 4
				</label> <label for="addons-4" class="form-check-label"> <input
					type="checkbox" name="addons" id="addons-4" value="addon5"
					class="form-check-input"> Addon 5
				</label>
			</div>

		</fieldset>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form>

</div>
<%@ include file="common/footer.jsp"%>