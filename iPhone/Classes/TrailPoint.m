#import "TrailPoint.h"

@implementation TrailPoint

@synthesize trail = _trail;
@synthesize connections = _connections;
@synthesize unresolvableLinks = _unresolvableLinks;

-(id) initWithParams:(NSInteger)id 
location:(CGPoint)p category:(NSString *)c summary:(NSString *)s title:(NSString *)t connections:(NSMutableSet *)connections {
    
	self = [super initWithParams:id location:p category:c title:t summary:s];
	if (self != nil) {
        _connections = connections;
	}
	return self;
}

- (void) dealloc {

    [_trail release];
    [_connections release];
    [_unresolvableLinks release];
	[super dealloc];
}

@end
