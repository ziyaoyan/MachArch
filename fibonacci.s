MOV r1, #8 				@sets r1 to #5; fib(5) //current
SUB R7, R7, R7			@sets R7 to #0
BL fib					@branch to fib
swi 0x11					@stops the program

fib:
	SUB sp, sp, #8		@subtracting 8 from stack pointer
	STR lr, [sp, #0]	@stores link to fib in stack
	STR r1, [sp, #4]	@stores current fib number in stack
	CMP r1, #1 			@sets conditional flag (r1-#1)
	BLGT else			@branch to else if r1>=1		
	
	@if not else
	ADD r7, r7, r1		@r7+=r1
	MOV pc, lr 			@change pc to lr
					
else:
	SUB r1, r1, #1		@fib(n-1)
	BL fib				@return to here if done recursing one branch
	
	SUB r1, r1, #1		@fib(n-2)
	BL fib
	
	LDR lr, [sp, #0]
	LDR r1, [sp, #4]
	ADD sp, sp, #8
	
	MOV pc, lr
