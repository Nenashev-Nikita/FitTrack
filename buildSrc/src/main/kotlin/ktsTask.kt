import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class MyCustomTask : DefaultTask() {

	@TaskAction
	fun calculateSum() {
		// Custom logic to calculate the sum of two numbers
		val num1 = 5
		val num2 = 7
		val sum = num1 + num2

		// Print the result
		println("Sum: $sum")
	}
}