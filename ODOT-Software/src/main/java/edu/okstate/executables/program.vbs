Dim objShell
Set objShell = WScript.CreateObject("WScript.Shell")
objShell.Run ("""C:\Program Files\Microsoft Office\root\Office16\WINPROJ.EXE""")
WScript.Sleep 1000
objShell.AppActivate "Project"
WScript.Sleep 1000
objShell.SendKeys "%O%OO"
WScript.Sleep 1000
objShell.SendKeys "C:\CPM-Software\src\main\java\edu\okstate\executables\Dummy.mpp{ENTER}"
WScript.Sleep 5000
objShell.SendKeys "%FOO"
WScript.Sleep 1000
objShell.SendKeys WScript.Arguments(0)
objShell.SendKeys "{ENTER}{ENTER}{ENTER}{ENTER}{TAB}{TAB} {TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{ENTER}{TAB}{TAB}{DOWN}{TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{TAB}{ENTER}{TAB}{ENTER}"
objShell.SendKeys "%HTATTotal{ENTER}^+{DOWN}%HI"