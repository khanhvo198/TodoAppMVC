$(document).on('ready', function() {
  const btnNewTask = $("#btn-new-task")

  console.log(btnNewTask)
  btnNewTask.on("click", function() {
    console.log("Hello")
  })
})

