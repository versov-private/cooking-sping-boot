import {Directive, Input, OnInit, TemplateRef, ViewContainerRef} from '@angular/core';
import {TokenStorageService} from "../services/auth/token-storage.service";

@Directive({
  selector: '[showAuth]'
})
export class ShowAuthDirective implements OnInit {

  condition: boolean;

  constructor(private tokenService: TokenStorageService, private viewContainer: ViewContainerRef, private templateRef: TemplateRef<any>) {}

  ngOnInit(): void {
    if( this.tokenService.isLoggedIn() && this.condition || !this.tokenService.isLoggedIn() && !this.condition) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }

  @Input() set showAuth(condition: boolean) {
    this.condition = condition;
  }


}
