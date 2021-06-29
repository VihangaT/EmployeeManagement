import {HttpErrorResponse} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
// import { EmptyError } from 'rxjs';
import {Employee} from './employee';
import {EmployeeService} from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'EmployeeManagementApp';
  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;

  constructor(private employeeService:EmployeeService){}
  ngOnInit(): void {
    this.getEmployees();
  }
;


public searchEmployees(key: string): void {};



  public getEmployees():void{
    this.employeeService.getEmployees().subscribe(
      (response: Employee[])=>{
        this.employees=response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    )
  };

  public onUpdateEmloyee(employee: Employee): void {
    this.employeeService.UpdateEmployees(employee).subscribe(
      (Response: Employee) => {
        console.log(Response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onDeleteEmloyee(employeeId: number): void {
    this.employeeService.DeleteEmployees(employeeId).subscribe(
      (Response: void) => {
        console.log(Response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddEmployee(addform: NgForm): void {
    document.getElementById("add-employee-form").click();
    this.employeeService.addEmployees(addform.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addform.reset();

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addform.reset();
      }
    )
  }

  public onOpenModal(employee: Employee,mode:string):void{
    const container =document.getElementById('main-container');
    const button=document.createElement('button');
    button.type='button';
    button.style.display='none';
    button.setAttribute('data-toggle','modal');
    if(mode== 'add'){
      button.setAttribute('data-target','#addEmployeeModel');
    }
    if(mode== 'edit'){
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if(mode== 'delete'){
      this.deleteEmployee = employee;
      button.setAttribute('data-target','#deleteEmployeeModel');
    }

    container.appendChild(button);
    button.click();
  }
}
